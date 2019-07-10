//****************************************************************************//
// @file CorreoBean.java
// 
// @description Backend para la administracion del catalogo de correos
// 
// @dependants
//  └── correo.xhtml
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_correo;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.CorreoService;
import com.itaca.callcenter.web.model.CorreoLazyDataModel;
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
public class CorreoBean {
    
    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(CorreoBean.class);

    @EJB
    private transient CorreoService correoService;
    
    @Getter
    @Setter
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private K_correo correo;
    @Getter
    private List<K_correo> list = new ArrayList<>();
    @Getter
    private boolean edicion = false;
    @Getter
    private CorreoLazyDataModel lazyDataModel;

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        list = correoService.getAll();
    }

    private void inicializarPropiedades() {
        correo = new K_correo();
        edicion = false;
        lazyDataModel = new CorreoLazyDataModel(correoService);
    }

    public void guardar() {
        logger.info("Guardando correo");
        try {
            if (edicion) {
                actualizar();
            } else {
                registrar();
            }
        } catch (Exception e) {
            logger.error("Error al guardar correo.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el correo");
        }
    }

    private void registrar() {
        if (correoService.getByCorreo(correo.getCorreo()) == null) {
            correoService.save(correo, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Cargo guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("El correo ya existe.");
        }
    }

    private void actualizar() {
        correoService.save(correo, usuarioLogueado);
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
