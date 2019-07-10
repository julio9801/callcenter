//****************************************************************************//
// @file PuestoService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_puesto
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.PuestoDAO;
import com.itaca.callcenter.domain.entities.K_puesto;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class PuestoService {
    private static final Logger logger = LogManager.getLogger(PuestoService.class);
    @EJB
    private PuestoDAO puestoDAO;
    @EJB
            LogService logService;
    
    public List<K_puesto> getAll() {
        return puestoDAO.findAll();
    }
    
    public void save(K_puesto puesto, Usuario user) {
        try {
            if (puesto.getId() == null) {
                logger.info("Guardando puesto.");
                puestoDAO.persist(puesto);
                logService.info(user,puesto.getId(),puesto.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando puesto.");
                puestoDAO.update(puesto);
                logService.info(user,puesto.getId(),puesto.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el puesto.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return puestoDAO.countAll();
    }
    
    public List<K_puesto> getResultList(int first, int pageSize) {
        return puestoDAO.findAll(first, pageSize);
    }
    
    public K_puesto getByPuesto(String str){
        return puestoDAO.findByPuesto(str);
    }
}
