//****************************************************************************//
// @file UsuariBean.java
//
// @description Documento no creado por el desarrollador original. Se modifica 
// para agregar roles disponibles
//
// @author Gerardo Blanco
// @date 10/01/2019
// @date 11/01/2019
//****************************************************************************//
package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.constant.RolConstants;
import com.itaca.callcenter.dao.RolDAO;
import com.itaca.callcenter.domain.entities.Rol;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.UsuarioService;
import com.itaca.callcenter.web.model.UsuarioLazyDataModel;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.PasswordUtil;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.Serializable;
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
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = -1741133965601564132L;
    private static final Logger logger = LogManager.getLogger(UsuarioBean.class);

    @EJB
    private transient UsuarioService usuarioService;
    @EJB
    private RolDAO rolDAO;

    @Getter
    @Setter
    private Usuario usuario;
    private Usuario usuarioLogueado;
    @Getter
    @Setter
    private Rol rol;
    @Setter
    @Getter
    private boolean habilitarEliminacion;
    @Getter
    private boolean edicion = false;
    @Getter
    private UsuarioLazyDataModel lazyDataModel;

    @Getter
    private List<Rol> roles = new ArrayList<>();
    

    @PostConstruct
    public void init() {
        usuarioLogueado = UsuarioUtil.getUsuarioAutenticado();
        inicializarPropiedades();
        this.roles = rolDAO.findAll();
//        this.empleados=servEmpleado.getAll();
    }

    private void inicializarPropiedades() {
        usuario = new Usuario();
        usuario.setRoles(new ArrayList<Rol>());
        habilitarEliminacion = false;

        edicion = false;
        rol = null;
        lazyDataModel = new UsuarioLazyDataModel(usuarioService);
    }

    public void guardar() {
        logger.info("Guardando usuario");
        final List<Rol> roles = new ArrayList<>();
        roles.add(rol);
        usuario.setRoles(roles);
        try {
            if (edicion) {
                actualizarUsuario();
            } else {
                registrarUsuario();
            }
        } catch (Exception e) {
            logger.error("Error al guardar usuario.", e);
            FacesUtil.addErrorMessage("No se pudo guardar el usuario");
        }
    }

    public boolean getMuestraPassword() {
        if (this.rol != null
                && (rol.getNombre().equals(RolConstants.ROLE_WEB_MASTER)
                || rol.getNombre().equals(RolConstants.ROLE_ADMIN)
                || rol.getNombre().equals(RolConstants.ROLE_SUPERVISOR)
                || rol.getNombre().equals(RolConstants.ROLE_CALL_CENTER)
                || rol.getNombre().equals(RolConstants.ROLE_EJECUTOR)
                || rol.getNombre().equals(RolConstants.ROLE_INFRAESTRUCTURA))) {
            return true;
        } else {
            return false;
        }
    }

    public void onRoleSelect() {

    }

    private boolean isItaca() {
        if (this.rol == null) {
            return false;
        }

        return (rol.getNombre().equals(RolConstants.ROLE_WEB_MASTER)
                || rol.getNombre().equals(RolConstants.ROLE_ADMIN)
                || rol.getNombre().equals(RolConstants.ROLE_SUPERVISOR)
                || rol.getNombre().equals(RolConstants.ROLE_CALL_CENTER)
                || rol.getNombre().equals(RolConstants.ROLE_EJECUTOR)
                || rol.getNombre().equals(RolConstants.ROLE_INFRAESTRUCTURA));
    }

    private void registrarUsuario() {

        if (usuarioService.getByEmail(usuario.getEmail()) == null) {

            usuario.setPassword(PasswordUtil.encode(usuario.getPassword()));
            usuario.setVerificado(true);
            usuarioService.save(usuario, usuarioLogueado);

            inicializarPropiedades();
            FacesUtil.addMessage("Usuario guardado correctamente.");
        } else {
            FacesUtil.addErrorMessage("La cuenta de correo ya está registrada, ingrese una diferente");
        }

    }

    private void actualizarUsuario() {
        if (usuario.getPassword() != null) {
            usuario.setPassword(PasswordUtil.encode(usuario.getPassword()));
        }
        usuarioService.save(usuario, usuarioLogueado);
        inicializarPropiedades();
        FacesUtil.addMessage("Usuario guardado.");
    }

    public String printRol(List<Rol> roles) {
        if (roles != null && roles.size() > 0) {
            return roles.get(0).getNombre();
        } else {
            return null;
        }

    }

    public void borrar() {
        logger.info("Borrando usuario");
        try {
            usuario.setActivo(false);
            usuarioService.save(usuario, usuarioLogueado);
            inicializarPropiedades();
            FacesUtil.addMessage("Usuario borrado.");
        } catch (Exception e) {
            logger.error("Error al borrar usuario.", e);
            FacesUtil.addErrorMessage("No se pudo borrar el usuario");
        }
    }

    public void onUsuarioSelect() {
        edicion = true;
        habilitarEliminacion = true;
        rol = usuario.getRoles().get(0);
    }

    public void cancelar() {
        inicializarPropiedades();
    }

}