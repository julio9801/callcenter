//****************************************************************************//
// @file SucursalService.java
// 
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_sucursal
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.SucursalDAO;
import com.itaca.callcenter.domain.entities.K_sucursal;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class SucursalService {
    private static final Logger logger = LogManager.getLogger(SucursalService.class);
    @EJB
    private SucursalDAO sucursalDAO;
    @EJB
            LogService logService;
    
    public List<K_sucursal> getAll() {
        return sucursalDAO.findAll();
    }
    
    public void save(K_sucursal sucursal, Usuario user) {
        try {
            if (sucursal.getId() == null) {
                System.out.print("Guardando sucursal.");
                sucursalDAO.persist(sucursal);
                logService.info(user,sucursal.getId(),sucursal.getClass().toString(),"Insert");
            } else {
                System.out.print("Actualizando sucursal.");
                sucursalDAO.update(sucursal);
                logService.info(user,sucursal.getId(),sucursal.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el sucursal.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return sucursalDAO.countAll();
    }
    
    public List<K_sucursal> getResultList(int first, int pageSize) {
        return sucursalDAO.findAll(first, pageSize);
    }
    
    public K_sucursal getBySucursal(String str){
        return sucursalDAO.findBySucursal(str);
    }
}
