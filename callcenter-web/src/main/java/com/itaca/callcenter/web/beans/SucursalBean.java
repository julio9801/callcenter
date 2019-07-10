//****************************************************************************//
// @file SucursalBean.java
// 
// @description Backend para la administracion del catalogo de sucursals
// 
// @dependants
//  └── sucursal.xhtml
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_sucursal;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.SucursalService;
import com.itaca.callcenter.web.model.SucursalLazyDataModel;
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
public class SucursalBean {
    
    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(SucursalBean.class);

    @EJB
    private transient SucursalService sucursalService;
    
    @Getter
    @Setter
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private K_sucursal sucursal;
    @Getter
    private List<K_sucursal> list = new ArrayList<>();
    @Getter
    private boolean edicion = false;
    @Getter
    private SucursalLazyDataModel lazyDataModel;

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        list = sucursalService.getAll();
    }

    private void inicializarPropiedades() {
        sucursal = new K_sucursal();
        edicion = false;
        lazyDataModel = new SucursalLazyDataModel(sucursalService);
    }

    public void guardar() {
        System.out.print("Guardando sucursal");
        try {
            if (edicion) {
                actualizar();
            } else {
                registrar();
            }
        } catch (Exception e) {
            logger.error("Error al guardar sucursal.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el sucursal");
        }
    }

    private void registrar() {
        if (sucursalService.getBySucursal(sucursal.getSucursal()) == null) {
            sucursalService.save(sucursal, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Cargo guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("El sucursal ya existe.");
        }
    }

    private void actualizar() {
        sucursalService.save(sucursal, usuarioLogueado);
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
