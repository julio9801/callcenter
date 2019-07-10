package com.itaca.callcenter.services;

import com.itaca.callcenter.dao.RegionDAO;
import com.itaca.callcenter.domain.entities.K_region;
import com.itaca.callcenter.domain.entities.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class RegionService {
    
    private static final Logger logger = LogManager.getLogger(RegionService.class);
    
    @EJB
            LogService logService;
    @EJB
    private RegionDAO regionDAO;
    
    

    
    //Acciones a BD
    public K_region guardar(K_region region,Usuario user) {
        logger.info("Ejecutando en region service guardar()" + region.getId());
        region = regionDAO.update(region);
        logService.info(user, region.getId(), "Region", "Update");
        return region;
    }
    
    public K_region inserta(K_region region,Usuario user) {
        logger.info("Ejecutando en region service inserta()" + region.getId());
        region =  regionDAO.persist(region);
        //empleado =  empleadoDAO.merge(empleado);
        logService.info(user, region.getId(), "Region", "Insert");
        return region;
    }
    
        //Querys
    public List<K_region> getAll(){
        return regionDAO.findAll();
    }
    
    public K_region getById(Integer id){
        return regionDAO.findById(id);
    }
    
    public K_region getByNombre(String str){
        return regionDAO.findByNombre(str);
    }
    
    public RegionDAO getRegionDAO() {
        return regionDAO;
    }
}