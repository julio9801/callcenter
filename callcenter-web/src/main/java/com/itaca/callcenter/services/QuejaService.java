//****************************************************************************//
// @file QuejaService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo Queja
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//
package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.CausaDAO;
import com.itaca.callcenter.dao.CorreoDAO;
import com.itaca.callcenter.dao.PuestoDAO;
import com.itaca.callcenter.dao.QuejaDAO;
import com.itaca.callcenter.dao.ReportadorDAO;
import com.itaca.callcenter.dao.SucursalDAO;
import com.itaca.callcenter.dao.TipoDAO;
import com.itaca.callcenter.domain.entities.K_causa;
import com.itaca.callcenter.domain.entities.K_correo;
import com.itaca.callcenter.domain.entities.K_puesto;
import com.itaca.callcenter.domain.entities.K_reportador;
import com.itaca.callcenter.domain.entities.K_sucursal;
import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class QuejaService {

    private static final Logger logger = LogManager.getLogger(QuejaService.class);
    @EJB
    private QuejaDAO quejaDAO;
    @EJB
    LogService logService;
    @EJB
    private CausaDAO causaDAO;
    @EJB
    private CorreoDAO correoDAO;
    @EJB
    private PuestoDAO puestoDAO;
    @EJB
    private ReportadorDAO reportadorDAO;
    @EJB
    private SucursalDAO sucursalDAO;
    @EJB
    private TipoDAO tipoDAO;

    public List<Queja> getAll() {
        return quejaDAO.findAll();
    }

    public void save(Queja queja, Usuario user) {
        try {
            if (queja.getId() == null) {
                logger.info("Guardando queja.");
                quejaDAO.persist(queja);
                logService.info(user, queja.getId(), queja.getClass().toString(), "Insert");
            } else {
                logger.info("Actualizando queja.");
                quejaDAO.update(queja);
                logService.info(user, queja.getId(), queja.getClass().toString(), "Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el queja.", e);
            throw e;
        }

    }

    public int countAll() {
        return quejaDAO.countAll();
    }

    public List<Queja> getResultList(int first, int pageSize) {
        return quejaDAO.findAll(first, pageSize);
    }

    public Queja getById(Integer id) {
        return quejaDAO.findById(id);
    }

    public List<Queja> getCatalog() {
        return quejaDAO.findAll();
    }

    public List<Queja> getCatalogByUsuario(Integer id) {
        return quejaDAO.findAllByUsuario(id);
    }

    public List<Queja> getCatalogByCorreoAndActivo(String str) {
        return quejaDAO.findAllByCorreoAndActivo(str);
    }

    public QuejaDAO getQuejaDAO() {
        return quejaDAO;
    }

    //Acciones a BD
    public List<String> validaAlta(Queja queja) {
        List<String> errors = new ArrayList<>();

        return errors;
    }

    public List<String> validaCarga(List<Queja> quejas) {
        List<String> errors = new ArrayList<>();
        String error;
        Boolean conError = true;

        for (int i = 0; i < quejas.size(); i++) {
            //Variables de control
            error = "[Fila " + (i + 2) + "]: ";
            System.out.print("Validando registro " + i);

            //Variables de validacion
            Queja queja = quejas.get(i);

            K_causa causa = causaDAO.findByCausa(queja.getCausaCarga());
            K_sucursal sucursal = sucursalDAO.findBySucursal(queja.getSucursalCarga());
            K_reportador reportador = reportadorDAO.findByReportador(queja.getReportadorCarga());
            K_puesto puesto = puestoDAO.findByPuesto(queja.getPuestoCarga());
            K_correo correo = correoDAO.findByCorreo(queja.getCorreoCarga());

            if (causa == null) {
                error = error.concat("No se encontro la causa: " + queja.getCausaCarga() + ".");
                errors.add(error);
            } else {
//                if (causa.getTipo().getId() != 5) {
//                    error = error.concat("La causa: " + queja.getCausaCarga() + " no es de tipo Kobra.");
//                    errors.add(error);
//                } else {
                    queja.setCausa(causa);
//                }
            }

            if (sucursal == null) {
                error = error.concat("No se encontro la sucursal: " + queja.getSucursalCarga() + ".");
                errors.add(error);
            } else {
                queja.setSucursal(sucursal);
            }
            
            if (reportador == null) {
                error = error.concat("No se encontro el reportador: " + queja.getReportadorCarga() + ".");
                errors.add(error);
            } else {
                queja.setReportador(reportador);
            }

            if (puesto == null) {
                error = error.concat("No se encontro el puesto: " + queja.getPuestoCarga() + ".");
                errors.add(error);
            } else {
                queja.setPuesto(puesto);
            }
            
            if (correo == null) {
                error = error.concat("No se encontro el correo: " + queja.getCorreoCarga() + ".");
                errors.add(error);
            } else {
                queja.setCorreo(correo);
            }
        }
        return errors;
    }

}
