//****************************************************************************//
// @file ReportadorBean.java
// 
// @description Backend para la administracion del catalogo de reportadors
// 
// @dependants
//  └── reportador.xhtml
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_reportador;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.ReportadorService;
import com.itaca.callcenter.web.model.ReportadorLazyDataModel;
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
public class ReportadorBean {
    
    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(ReportadorBean.class);

    @EJB
    private transient ReportadorService reportadorService;
    
    @Getter
    @Setter
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private K_reportador reportador;
    @Getter
    private List<K_reportador> list = new ArrayList<>();
    @Getter
    private boolean edicion = false;
    @Getter
    private ReportadorLazyDataModel lazyDataModel;

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        list = reportadorService.getAll();
    }

    private void inicializarPropiedades() {
        reportador = new K_reportador();
        edicion = false;
        lazyDataModel = new ReportadorLazyDataModel(reportadorService);
    }

    public void guardar() {
        logger.info("Guardando reportador");
        try {
            if (edicion) {
                actualizar();
            } else {
                registrar();
            }
        } catch (Exception e) {
            logger.error("Error al guardar reportador.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el reportador");
        }
    }

    private void registrar() {
        if (reportadorService.getByReportador(reportador.getTipo()) == null) {
            reportadorService.save(reportador, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Cargo guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("El reportador ya existe.");
        }
    }

    private void actualizar() {
        reportadorService.save(reportador, usuarioLogueado);
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
