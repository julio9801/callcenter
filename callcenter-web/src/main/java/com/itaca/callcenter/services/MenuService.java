package com.itaca.callcenter.services;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itaca.callcenter.dao.RolDAO;
import com.itaca.callcenter.domain.entities.Menu;
import com.itaca.callcenter.domain.entities.Rol;

@Stateless
public class MenuService {
    
    private static final Logger logger = LogManager.getLogger(MenuService.class);
    @EJB
            LogService logService;
    @EJB
    private RolDAO rolDAO;
    
    public Set<Menu> getMenuByRol(String nombreRol) {
        logger.info("Ejecutando getMenu()");
        Set<Menu> menus = null;
        Rol rol =  rolDAO.findByName(nombreRol);
        logger.info("menus" + rol.getMenus());
        if(rol != null){
            menus = rol.getMenus();
        }
        return menus;
    }
    
}
