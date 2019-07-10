package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_region;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.LogService;
import com.itaca.callcenter.services.RegionService;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.StringUtils;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@ViewScoped
public class RegionBean implements Serializable {
    private static final Logger logger = LogManager.getLogger(UsuarioBean.class);
    private static final long serialVersionUID = -7820209981001289767L;
    
    //Services
    @EJB
    private RegionService regionesService;
    @EJB
    private LogService logService;
    
    //Variables
    @Getter
    private List<K_region> listRegiones;
    @Getter
    private K_region region;
    
    //Methods
    @PostConstruct
    public void init() {
        logger.info("Ejecutando Regiones init()");
        String id = FacesUtil.getRequestParameter("id");
        if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id)) {
            region = regionesService.getById(Integer.valueOf(id));
        } else {
            region = new K_region();
        }
        this.listRegiones = regionesService.getAll();
    }
    
    public void guarda() {
        logger.info("Ejecutando en regiones guarda()");
        if (region != null) {
            Usuario user = UsuarioUtil.getUsuarioAutenticado();
            regionesService.guardar(region,user);
            FacesUtil.addMessage("Regiones guardada correctamente.");
        }
    }
}
