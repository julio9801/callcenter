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

import com.itaca.callcenter.dao.RespuestaDAO;
import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Respuesta;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class RespuestaService {
    private static final Logger logger = LogManager.getLogger(RespuestaService.class);
    @EJB
    private RespuestaDAO respuestaDAO;
    @EJB
            LogService logService;
    
    public List<Respuesta> getAll() {
        return respuestaDAO.findAll();
    }
    
    public void save(Respuesta respuesta, Usuario user) {
        try {
            if (respuesta.getId() == null) {
                logger.info("Guardando respuesta.");
                respuestaDAO.persist(respuesta);
                logService.info(user,respuesta.getId(),respuesta.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando respuesta.");
                respuestaDAO.update(respuesta);
                logService.info(user,respuesta.getId(),respuesta.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el respuesta.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return respuestaDAO.countAll();
    }
    
    public List<Respuesta> getResultList(int first, int pageSize) {
        return respuestaDAO.findAll(first, pageSize);
    }
    
    public Respuesta getById(Integer id){
        return respuestaDAO.findById(id);
    }
    
    public List<Respuesta> getCatalog() {
        return respuestaDAO.findAll();
    }
    
    public List<Respuesta> getCatalogByUsuario(Integer id) {
        return respuestaDAO.findAllByUsuario(id);
    }
    
    public List<Respuesta> getCatalogByQueja(Integer id) {
        return respuestaDAO.findAllByQueja(id);
    }
    
    //Acciones a BD
    public List<String> validaAlta(Queja queja){
        List<String> errors = new ArrayList<>();
        
        return errors;
    }
}
