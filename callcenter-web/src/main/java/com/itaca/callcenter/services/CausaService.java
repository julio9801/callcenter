//****************************************************************************//
// @file CausaService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_causa
//
// @author Gerardo Blanco
// @date 07/01/2019
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.CausaDAO;
import com.itaca.callcenter.domain.entities.K_causa;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class CausaService {
    private static final Logger logger = LogManager.getLogger(CausaService.class);
    @EJB
    private CausaDAO causaDAO;
    @EJB
            LogService logService;
    
    public List<K_causa> getAll() {
        return causaDAO.findAll();
    }
    
    public void save(K_causa causa, Usuario user) {
        try {
            if (causa.getId() == null) {
                logger.info("Guardando causa.");
                causaDAO.persist(causa);
                logService.info(user,causa.getId(),causa.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando causa.");
                causaDAO.update(causa);
                logService.info(user,causa.getId(),causa.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el causa.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return causaDAO.countAll();
    }
    
    public List<K_causa> getResultList(int first, int pageSize) {
        return causaDAO.findAll(first, pageSize);
    }
    
    public K_causa getByCausa(String str){
        return causaDAO.findByCausa(str);
    }
    
    public List<K_causa> getByTipo(int id){
        return causaDAO.findByTipo(id);
    }
}
