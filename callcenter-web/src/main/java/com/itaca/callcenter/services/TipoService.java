//****************************************************************************//
// @file TipoService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_tipo
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.TipoDAO;
import com.itaca.callcenter.domain.entities.K_tipo;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class TipoService {
    private static final Logger logger = LogManager.getLogger(TipoService.class);
    @EJB
    private TipoDAO tipoDAO;
    @EJB
            LogService logService;
    
    public List<K_tipo> getAll() {
        return tipoDAO.findAll();
    }
    
    public void save(K_tipo tipo, Usuario user) {
        try {
            if (tipo.getId() == null) {
                logger.info("Guardando tipo.");
                tipoDAO.persist(tipo);
                logService.info(user,tipo.getId(),tipo.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando tipo.");
                tipoDAO.update(tipo);
                logService.info(user,tipo.getId(),tipo.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el tipo.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return tipoDAO.countAll();
    }
    
    public List<K_tipo> getResultList(int first, int pageSize) {
        return tipoDAO.findAll(first, pageSize);
    }
    
    public K_tipo getByTipo(String str){
        return tipoDAO.findByTipo(str);
    }
}
