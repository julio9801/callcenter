//****************************************************************************//
// @file CorreoService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_correo
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.CorreoDAO;
import com.itaca.callcenter.domain.entities.K_correo;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class CorreoService {
    private static final Logger logger = LogManager.getLogger(CorreoService.class);
    @EJB
    private CorreoDAO correoDAO;
    @EJB
            LogService logService;
    
    public List<K_correo> getAll() {
        return correoDAO.findAll();
    }
    
    public void save(K_correo correo, Usuario user) {
        try {
            if (correo.getId() == null) {
                logger.info("Guardando correo.");
                correoDAO.persist(correo);
                logService.info(user,correo.getId(),correo.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando correo.");
                correoDAO.update(correo);
                logService.info(user,correo.getId(),correo.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el correo.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return correoDAO.countAll();
    }
    
    public List<K_correo> getResultList(int first, int pageSize) {
        return correoDAO.findAll(first, pageSize);
    }
    
    public K_correo getByCorreo(String str){
        return correoDAO.findByCorreo(str);
    }
}
