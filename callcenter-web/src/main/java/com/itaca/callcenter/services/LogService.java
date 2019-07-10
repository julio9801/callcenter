//****************************************************************************//
// @file LogService.java
// 
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo Log
//
// @author Gerardo Blanco
// @date 04/01/2019
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.LogDAO;
import com.itaca.callcenter.domain.entities.Log;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class LogService {

    private static final Logger logger = LogManager.getLogger(LogService.class);

    @EJB
    private LogDAO logDAO;
    
    
  public void info(Usuario user,int idRelacion,String objeto,String accion){
        Log log=new Log();
        log.setFecha(new Date());
        log.setAccion(accion);
        log.setObjeto(objeto);
        log.setIdElement(idRelacion);
        log.setUsuario(user);
        logDAO.persist(log);
    }
    
    public List<Log> getCatalog() {
        return logDAO.findAll();
    }
}
