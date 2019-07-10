//****************************************************************************//
// @file AreaBean.java
// 
// @description Backend para la administracion del catalogo de areas
// 
// @dependants
//  └── area.xhtml
//
// @author Gerardo Blanco
// @date 10/01/2019
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_area;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.AreaService;
import com.itaca.callcenter.web.model.AreaLazyDataModel;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@ViewScoped
public class AreaBean {
    
    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(AreaBean.class);

    @EJB
    private transient AreaService areaService;
    
    @Getter
    @Setter
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private K_area area;
    @Getter
    private List<K_area> list = new ArrayList<>();
    @Getter
    private boolean edicion = false;
    @Getter
    private AreaLazyDataModel lazyDataModel;

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        list = areaService.getAll();
    }

    private void inicializarPropiedades() {
        area = new K_area();
        edicion = false;
        lazyDataModel = new AreaLazyDataModel(areaService);
    }

    public void guardar() {
        logger.info("Guardando area");
        try {
            if (edicion) {
                actualizar();
            } else {
                registrar();
            }
        } catch (Exception e) {
            logger.error("Error al guardar area.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el area");
        }
    }

    private void registrar() {
        if (areaService.getByArea(area.getArea()) == null) {
            areaService.save(area, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Area guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("El area ya existe.");
        }
    }

    private void actualizar() {
        areaService.save(area, usuarioLogueado);
        inicializarPropiedades();
        FacesUtil.addMessage("Area guardado.");
    }

    public void onSelect() {
        edicion = true;
    }

    public void cancelar() {
        inicializarPropiedades();
    }
}
