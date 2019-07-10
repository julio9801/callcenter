package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.Documentos;
import com.itaca.callcenter.domain.entities.K_correo;
import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Respuesta;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.DocumentoService;
import com.itaca.callcenter.services.QuejaService;
import com.itaca.callcenter.services.RespuestaService;
import com.itaca.callcenter.services.SenderService;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.StringUtils;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@ViewScoped
public class QuejaBean implements Serializable {

    private static final Logger logger = LogManager.getLogger(UsuarioBean.class);
    private static final long serialVersionUID = -7820209981001289767L;

    //Services
    @EJB
    private QuejaService quejaService;
    @EJB
    private RespuestaService respuestaService;
    @EJB
    private DocumentoService documentoService;
    @EJB
    private SenderService senderService;

    //Variables
    @Getter
    private List<Respuesta> list;
    @Getter
    private List<Documentos> listDocumentos;
    @Getter
    private Queja queja;
    @Getter
    private Respuesta respuesta;
    @Getter
    @Setter
    private String textoBoton = "Cerrar Queja";
    @Getter
    @Setter
    private Integer role;
    @Getter
    @Setter
    private K_correo correoNuevo;

    //Methods
    @PostConstruct
    public void init() {
        logger.info("Ejecutando Quejas init()");
        String id = FacesUtil.getRequestParameter("id");
        String men = FacesUtil.getRequestParameter("mensaje");
        if (StringUtils.isNotBlank(men)) {
            men = men.replace('+', ' ');
            if (men.contains("Respuesta guardada correctamente.")) {
                FacesUtil.addMessage(men);
            }
            FacesUtil.addErrorMessage(men);
        }

        respuesta = new Respuesta();
        if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)) {
            queja = quejaService.getById(Integer.valueOf(id));
            list = respuestaService.getCatalogByQueja(Integer.valueOf(id));
            listDocumentos = documentoService.getCatalogByQueja(Integer.valueOf(id));
            if (!queja.getEstatus().contains("activo")) {
                textoBoton = "Queja Cerrada";
            }
            Usuario user = UsuarioUtil.getUsuarioAutenticado();
            role = user.getRoles().get(0).getId().intValue();
        } else {
            queja = new Queja();
        }

    }

    public String responder() {
        System.out.println("Voy a guardar Respuesta");
        try {
            if (queja.getEstatus().contains("activo")) {
                if (respuesta.getResponde().length() > 1 && respuesta.getRespuesta().length() > 1) {
                    Usuario user = UsuarioUtil.getUsuarioAutenticado();
                    respuesta.setFecha(new Date());
                    respuesta.setUsuario(user);
                    respuesta.setQueja(queja);
                    respuestaService.save(respuesta, user);
                    try {
                        senderService.cargaRespuesta(queja, respuesta, user);
                    } catch (Exception e) {
                        System.out.print("Error->" + e.getMessage());
                    }
                    //FacesUtil.addMessage("Respuesta guardada correctamente.");
                    String exito = "Respuesta guardada correctamente.";
                    return FacesUtil.getQuejaUrl(queja.getId().toString(), exito);
                } else {
                    // FacesUtil.addErrorMessage("Favor de llenar todos los campos");
                    String err = "Favor de llenar todos los campos";
                    return FacesUtil.getQuejaUrl(queja.getId().toString(), err);
                }
            } else {
                String err = "La queja se encuentra cerrada";
                return FacesUtil.getQuejaUrl(queja.getId().toString(), err);
            }
        } catch (Exception e) {
            String err = "No se pudo registrar la respuesta";
            return FacesUtil.getQuejaUrl(queja.getId().toString(), err);
        }
//        return FacesUtil.getQuejaUrl(queja.getId().toString(),"Error no identificado");
    }

    public void cerrar() {
        System.out.println("Voy a cerrar Queja");
        if (!queja.getEstatus().contains("cerrado") && !list.isEmpty()) {
            Usuario user = UsuarioUtil.getUsuarioAutenticado();
            queja.setCambio(new Date());
            queja.setEstatus("cerrado");
            quejaService.save(queja, user);
            FacesUtil.addMessage("Queja cerrada correctamente.");
        } else {
            FacesUtil.addMessage("La queja se encuentra cerrada o no hay ninguna respuesta");
        }
        //return FacesUtil.getQuejaUrl(queja.getId().toString());
    }

    public void reasignarQueja() {
        String exito = "";
        try {
            if (queja != null && correoNuevo != null) {
                Usuario user = UsuarioUtil.getUsuarioAutenticado();
                System.out.println("Cambiando asignacion");

                queja.setCorreo(correoNuevo);
                queja.setCambio(new Date());
                queja.setEstatus("reasignada");
                quejaService.save(queja, user);
                try {
                    senderService.cargaReasignacion(queja, user);
                } catch (Exception e) {
                    System.out.print("Error->" + e.getMessage());
                }
                exito = "Respuesta guardada correctamente.";
            } else {
                throw new Exception("Favor de seleccionar un correo");
            }
        } catch (Exception e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
        FacesUtil.addMessage(exito);
    }

}
