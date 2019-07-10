//****************************************************************************//
// @file PuestoBean.java
// 
// @description Backend para la administracion del catalogo de puestos
// 
// @dependants
//  └── puesto.xhtml
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_puesto;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.PuestoService;
import com.itaca.callcenter.web.model.PuestoLazyDataModel;
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
public class PuestoBean {
    
    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(PuestoBean.class);

    @EJB
    private transient PuestoService puestoService;
    
    @Getter
    @Setter
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private K_puesto puesto;
    @Getter
    private List<K_puesto> list = new ArrayList<>();
    @Getter
    private boolean edicion = false;
    @Getter
    private PuestoLazyDataModel lazyDataModel;

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        list = puestoService.getAll();
    }

    private void inicializarPropiedades() {
        puesto = new K_puesto();
        edicion = false;
        lazyDataModel = new PuestoLazyDataModel(puestoService);
    }

    public void guardar() {
        logger.info("Guardando puesto");
        try {
            if (edicion) {
                actualizar();
            } else {
                registrar();
            }
        } catch (Exception e) {
            logger.error("Error al guardar puesto.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el puesto");
        }
    }

    private void registrar() {
        if (puestoService.getByPuesto(puesto.getPuesto()) == null) {
            puestoService.save(puesto, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Cargo guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("El puesto ya existe.");
        }
    }

    private void actualizar() {
        puestoService.save(puesto, usuarioLogueado);
        inicializarPropiedades();
        FacesUtil.addMessage("Cargo guardado.");
    }

    public void onSelect() {
        edicion = true;
    }

    public void cancelar() {
        inicializarPropiedades();
    }
}
