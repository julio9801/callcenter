//****************************************************************************//
// @file CargaQuejassBean.java
//
// @description Controlador para para la consulta de quejas. 
//
// @dependants
//  └── altaQueja.xhtml
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//
package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.QuejaService;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@ViewScoped
public class ListadoQuejasBean implements Serializable {

    private static final Logger logger = LogManager.getLogger(UsuarioBean.class);
    private static final long serialVersionUID = -7820209981001289767L;

    @EJB
    private QuejaService quejaService;
    @Getter
    private List<Queja> list;

    String porUsuario = "/callcenter-web/pages/quejas/listadoUsuario.xhtml";

    @PostConstruct
    public void init() {
        Usuario user = UsuarioUtil.getUsuarioAutenticado();
        String uri = FacesUtil.getUrl();
        int rol = user.getRoles().get(0).getId().intValue();
        if (rol == 0 || rol == 1 || rol == 2) {
            list = quejaService.getCatalog();
        } else {
            if (rol == 4) {
                list = quejaService.getCatalogByCorreoAndActivo(user.getEmail());
            } else {
                if (uri.equals(porUsuario)) {
                    list = quejaService.getCatalogByUsuario(user.getId().intValue());
                } else {
                    list = quejaService.getCatalog();
                }
            }
        }
    }
}
