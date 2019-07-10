package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.DocumentoDAO;
import com.itaca.callcenter.dao.RespuestaDAO;
import com.itaca.callcenter.domain.entities.Documentos;
import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class DocumentoService {
    private static final Logger logger = LogManager.getLogger(DocumentoService.class);
    @EJB
    private DocumentoDAO documentoDAO;
    @EJB
            LogService logService;
    
    public List<Documentos> getAll() {
        return documentoDAO.findAll();
    }
    
    public void save(Documentos documento, Usuario user) {
        try {
            if (documento.getId() == null) {
                logger.info("Guardando documento.");
                documentoDAO.persist(documento);
                logService.info(user,documento.getId(),documento.getClass().toString(),"Insert");
            } else {
                logger.info("Actualizando documento.");
                documentoDAO.update(documento);
                logService.info(user,documento.getId(),documento.getClass().toString(),"Update");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el documento.", e);
            throw e;
        }
        
    }
    public Documentos getById(Integer id){
        return documentoDAO.findById(id);
    }
    
    public List<Documentos> getCatalog() {
        return documentoDAO.findAll();
    }
    
    public List<Documentos> getCatalogByQueja(Integer id) {
        return documentoDAO.findAllByQueja(id);
    }
    
    //Acciones a BD
    public List<String> validaAlta(Queja queja){
        List<String> errors = new ArrayList<>();
        
        return errors;
    }
}
