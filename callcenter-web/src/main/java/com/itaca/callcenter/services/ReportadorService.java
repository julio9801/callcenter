//****************************************************************************//
// @file ReportadorService.java
//
// @description Intermediario entre backEnd y querys relacionados con objetos
//    de tipo K_reportador
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.ReportadorDAO;
import com.itaca.callcenter.domain.entities.K_reportador;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class ReportadorService {
    private static final Logger logger = LogManager.getLogger(ReportadorService.class);
    @EJB
    private ReportadorDAO reportadorDAO;
    @EJB
            LogService logService;
    
    public List<K_reportador> getAll() {
        return reportadorDAO.findAll();
    }
    
    public void save(K_reportador reportador, Usuario user) {
        try {
            if (reportador.getId() == null) {
                logger.info("Guardando reportador.");
                reportadorDAO.persist(reportador);
                logService.info(user,reportador.getId(),reportador.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando reportador.");
                reportadorDAO.update(reportador);
                logService.info(user,reportador.getId(),reportador.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el reportador.", e);
            throw e;
        }
        
    }
    
    public int countAll() {
        return reportadorDAO.countAll();
    }
    
    public List<K_reportador> getResultList(int first, int pageSize) {
        return reportadorDAO.findAll(first, pageSize);
    }
    
    public K_reportador getByReportador(String str){
        return reportadorDAO.findByReportador(str);
    }
}
