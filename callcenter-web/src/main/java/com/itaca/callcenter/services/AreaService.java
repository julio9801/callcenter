//****************************************************************************//
// @file AreaService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_area
//
// @author Gerardo Blanco
// @date 10/01/2019
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.AreaDAO;
import com.itaca.callcenter.domain.entities.K_area;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class AreaService {
    private static final Logger logger = LogManager.getLogger(AreaService.class);
    @EJB
    private AreaDAO areaDAO;
    @EJB
            LogService logService;
    
    public List<K_area> getAll() {
        return areaDAO.findAll();
    }
    
    public void save(K_area area, Usuario user) {
        try {
            if (area.getId() == null) {
                logger.info("Guardando area.");
                areaDAO.persist(area);
                logService.info(user,area.getId(),area.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando area.");
                areaDAO.update(area);
                logService.info(user,area.getId(),area.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el area.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return areaDAO.countAll();
    }
    
    public List<K_area> getResultList(int first, int pageSize) {
        return areaDAO.findAll(first, pageSize);
    }
    
    public K_area getByArea(String str){
        return areaDAO.findByArea(str);
    }
}
