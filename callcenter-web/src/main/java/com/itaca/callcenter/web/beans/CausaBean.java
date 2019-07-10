//****************************************************************************//
// @file CausaBean.java
//
// @description Backend para la administracion del catalogo de causas
//
// @dependants
//  └── causa.xhtml
//
// @author Gerardo Blanco
// @date 07/01/2019
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.K_causa;
import com.itaca.callcenter.domain.entities.K_tipo;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.CausaService;
import com.itaca.callcenter.services.TipoService;
import com.itaca.callcenter.web.model.CausaLazyDataModel;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@ViewScoped
public class CausaBean {
    
    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(CausaBean.class);
    
    @EJB
    private transient CausaService causaService;
    @EJB
    private transient TipoService tipoService;
    
    @Getter
    @Setter
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private K_causa causa;
    @Getter
    private List<K_causa> list = new ArrayList<>();
    @Getter
    private List<K_tipo> listTipos = new ArrayList<>();
    @Getter
    private boolean edicion = false;
    @Getter
    private CausaLazyDataModel lazyDataModel;
    @Getter
    private List<SelectItem> causas;
    @Getter
    private List<SelectItem> categories;

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        
        list = causaService.getAll();
        listTipos = tipoService.getAll();
        
        categories = makeList(list,listTipos);
    }
    
    private List<SelectItem> makeList(List<K_causa> list,List<K_tipo> listTipos){
        categories = new ArrayList<SelectItem>();
        //Loop de tipos
        for(int j=0; j<listTipos.size();j++){
            SelectItemGroup tipo = new SelectItemGroup(listTipos.get(j).getTipo());
            //Loop get causas
            causas = new ArrayList<SelectItem>();
            for(int i=0; i<list.size();i++){
                //Si causa pertenece al tipo
                if(listTipos.get(j).equals(list.get(i).getTipo())){
                    SelectItem causaNueva = new SelectItem(list.get(i).getCausa());
                    System.out.print("Causa: "+causaNueva);
                    causas.add(causaNueva);
                }
            }
            //List a Array
            SelectItem[] array = new SelectItem[causas.size()];
            causas.toArray(array);
            //Save subitems y items
            tipo.setSelectItems(array);
            categories.add(tipo);
        }
        return categories;
    }
    
    private void inicializarPropiedades() {
        causa = new K_causa();
        edicion = false;
        lazyDataModel = new CausaLazyDataModel(causaService);
    }
    
    public void guardar() {
        logger.info("Guardando causa");
        try {
            if (edicion) {
                actualizar();
            } else {
                registrar();
            }
        } catch (Exception e) {
            logger.error("Error al guardar causa.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el causa");
        }
    }
    
    private void registrar() {
        if (causaService.getByCausa(causa.getCausa()) == null) {
            causaService.save(causa, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Cargo guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("El causa ya existe.");
        }
    }
    
    private void actualizar() {
        causaService.save(causa, usuarioLogueado);
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
