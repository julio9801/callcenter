//****************************************************************************//
// @file TipoBean.java
// 
// @description Backend para la administracion del catalogo de tipos
// 
// @dependants
//  └── tipo.xhtml
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_tipo;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.TipoService;
import com.itaca.callcenter.web.model.TipoLazyDataModel;
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
public class TipoBean {
    
    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(TipoBean.class);

    @EJB
    private transient TipoService tipoService;
    
    @Getter
    @Setter
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private K_tipo tipo;
    @Getter
    private List<K_tipo> list = new ArrayList<>();
    @Getter
    private boolean edicion = false;
    @Getter
    private TipoLazyDataModel lazyDataModel;

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        list = tipoService.getAll();
    }

    private void inicializarPropiedades() {
        tipo = new K_tipo();
        edicion = false;
        lazyDataModel = new TipoLazyDataModel(tipoService);
    }

    public void guardar() {
        logger.info("Guardando tipo");
        try {
            if (edicion) {
                actualizar();
            } else {
                registrar();
            }
        } catch (Exception e) {
            logger.error("Error al guardar tipo.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el tipo");
        }
    }

    private void registrar() {
        if (tipoService.getByTipo(tipo.getTipo()) == null) {
            tipoService.save(tipo, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Cargo guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("El tipo ya existe.");
        }
    }

    private void actualizar() {
        tipoService.save(tipo, usuarioLogueado);
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
