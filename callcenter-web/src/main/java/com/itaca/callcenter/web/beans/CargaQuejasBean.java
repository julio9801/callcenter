//****************************************************************************//
// @file CargaQuejassBean.java
//
// @description Controlador para para la carga de quejas. Cuenta con funciones
//  para la administracion de entradas y salidas como mensajes de exito y error.
//
// @dependants
//  └── altaQueja.xhtml
//
// @author Gerardo Blanco
// @date 10/01/2019
//****************************************************************************//
package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_causa;
import com.itaca.callcenter.domain.entities.K_tipo;
import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.CausaService;
import com.itaca.callcenter.services.LogService;
import com.itaca.callcenter.services.QuejaService;
import com.itaca.callcenter.services.SenderService;
import com.itaca.callcenter.utilities.FactoryParser;
import com.itaca.callcenter.utilities.FactoryValidatorQuejas;
import com.itaca.callcenter.utilities.ParserRegistros;
import com.itaca.callcenter.utilities.QuejasObject;
import com.itaca.callcenter.utilities.UtilitiesException;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CargaQuejasBean implements Serializable {

    private static final long serialVersionUID = 8518019008524007840L;
    private static final Logger logger = LogManager.getLogger(CargaQuejasBean.class);

    //Services
    @EJB
    QuejaService quejaService;
    @EJB
    CausaService causaService;
    @EJB
    SenderService senderService;
    @EJB
    LogService logService;

    //Variables
    Usuario user;
    @Getter
    private Queja queja;
    @Getter
    @Setter
    private String idProducto;
    @Getter
    @Setter
    private String producto;
    @Getter
    private List<K_causa> list = new ArrayList<>();

    //Methods
    @PostConstruct
    public void init() {
        idProducto = FacesUtil.getRequestParameter("productoId");
        if (idProducto != null) {
            list = causaService.getByTipo(Integer.parseInt(idProducto));
            queja = new Queja();
        }
    }

    public String seleccionProducto() {
        return "/pages/quejas/altaQuejaInformacion.xhtml"
                + "?faces-redirect=true&productoId=" + producto;
    }

    public void guardar() {
        if (queja != null) {
            try {
                if (!queja.getReportador().getTipo().contains("ANONIMO")) {
                    if (queja.getCliente().isEmpty() || queja.getTelefono().isEmpty()) {
                        throw new Exception("Se tiene que llenar los campos de cliente y telefono");
                    }
                }

                user = UsuarioUtil.getUsuarioAutenticado();
                System.out.print("Queja->" + queja);
                System.out.print("Grupo->" + queja.getGrupo());
                System.out.print("Causa->" + queja.getCausa());
                queja.setEstatus("activo");
                queja.setCambio(new Date());
                queja.setRegistro(new Date());
                queja.setUsuario(user);

                //(BK) en validacion con la base
                //List<String> errors = quejaService.validaAlta(queja);
                //
                quejaService.save(queja, user);
                senderService.cargaQueja(queja, user);
                FacesUtil.addMessage("Queja guardada correctamente. \n  Favor de volver a cargar la pagina antes de cargar de nuevo");
            } catch (Exception e) {
                FacesUtil.addErrorMessage("No se pudo registrar la queja (E): " + e.getMessage());
            }
        }
    }

    public void errores(List<String> errores) {
        FacesUtil.addErrorMessage("Ocurrieron los siguentes errores en la carga:");
        for (String error : errores) {
            FacesUtil.addErrorMessage(error);
        }
    }

    public void guardaArchivo(FileUploadEvent event) {
        logger.info("Ejecutando en quejas guardaArchivo()");
        final UploadedFile uploadedFile = event.getFile();
        EntityManager entityManager = quejaService.getQuejaDAO().getEntityManager();
        user = UsuarioUtil.getUsuarioAutenticado();

        if (uploadedFile != null) {
            try {
                //(UE) & PARSER
                InputStream input = uploadedFile.getInputstream();
                QuejasObject quejasObject = new QuejasObject();
                FactoryValidatorQuejas factoryValidatorQuejas = new FactoryValidatorQuejas();
                ParserRegistros<Queja> parser = FactoryParser.getInstance().createParserQuejas(input, entityManager, quejasObject, factoryValidatorQuejas.getmMapValidators().size());
                List<Queja> quejas = parser.parse(factoryValidatorQuejas);

                System.out.print("Termina default");
                List<String> errors = quejaService.validaCarga(quejas);
                //Impresion de errores o guardar cambios
                if (errors.size() > 0) {
                    errores(errors);
                } else {
                    for (Queja quejaSave : quejas) {
                        guardarAutomatico(quejaSave);
                    }
                    FacesUtil.addMessage("Las quejas han sido registradas correctamente.");
                }
                //Inicio de Errores           
            } catch (UtilitiesException e) {
                logger.info(e.toString() + e.getMessage());
                FacesUtil.addErrorMessage("No se pudo registrar las Quejas (PS): " + e.getMessage());
                List<String> err = e.getErrors();
                if (err != null) {
                    err.forEach((cadError) -> {
                        FacesUtil.addErrorMessage(cadError);
                    });
                }
                logService.info(user, 1, "Quejas Masiva", "Error(PS)");
            } catch (IOException | NumberFormatException e) {
                logger.error("IOE|NFE: " + e.toString() + e.getMessage());
                FacesUtil.addErrorMessage("No se pudo registrar las quejas (IO):");
                logService.info(user, 1, "Quejas Masiva", "Error(IO)");
            } catch (Exception e) {
                logger.info(e.toString() + e.getMessage());
                FacesUtil.addErrorMessage("No se pudo registrar las Quejas (E): " + e.getMessage());
                System.out.print(e);
                e.printStackTrace();
                logService.info(user, 1, "Quejas Masiva", "Error(E)");
            }
        }
    }

    public void guardarAutomatico(Queja queja) throws Exception {
        try {
            user = UsuarioUtil.getUsuarioAutenticado();
            queja.setEstatus("activo");
            queja.setCambio(new Date());
            queja.setRegistro(new Date());
            queja.setUsuario(user);
            quejaService.save(queja, user);
            senderService.cargaQueja(queja, user);
        } catch (Exception e) {
            logService.info(user, 1, "Queja", "Error(I)");
            logger.error("No se pudo guardar la queja", e);
            throw e;
        }
    }

}
