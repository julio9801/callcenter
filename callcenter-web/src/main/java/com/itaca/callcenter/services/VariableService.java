//****************************************************************************//
// @file VariableService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_variable
//
// @author Gerardo Blanco
// @date 27/12/2018
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.VariableDAO;
import com.itaca.callcenter.domain.entities.K_variable;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class VariableService {
    private static final Logger logger = LogManager.getLogger(VariableService.class);
    @EJB
    private VariableDAO variableDAO;
    @EJB
            LogService logService;
    
    public List<K_variable> getAll() {
        return variableDAO.findAll();
    }
    
//    public void save(K_variable variable, Usuario user) {
//        try {
//            if (variable.getId() == null) {
//                logger.info("Guardando variable.");
//                variableDAO.persist(variable);
//                logService.info(user,variable.getId(),variable.getClass().toString(),"Insert");
//            } else {
//                logger.info("Actualizando variable.");
//                variableDAO.update(variable);
//                logService.info(user,variable.getId(),variable.getClass().toString(),"Update");
//            }
//        } catch (Exception e) {
//            logger.error("Error al guardar el variable.", e);
//            throw e;
//        }
//        
//    }
    
    public int countAll() {
        return variableDAO.countAll();
    }
    
    public List<K_variable> getResultList(int first, int pageSize) {
        return variableDAO.findAll(first, pageSize);
    }
    
    public K_variable getByValor(String str){
        return variableDAO.findByValor(str);
    }
    
     public K_variable getById(String id){
        return variableDAO.findById(id);
    }
}
