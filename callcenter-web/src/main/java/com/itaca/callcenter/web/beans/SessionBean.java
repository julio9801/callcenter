//****************************************************************************//
// @file SessionBean.java
//
// @description Documento no creado por el desarrollador original. Se modifica 
// para agregar roles disponibles
//
// @author Gerardo Blanco
// @date 10/01/2019
// @date 11/01/2019
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.itaca.callcenter.web.utils.RolConstants;
import com.itaca.callcenter.domain.entities.Rol;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.web.utils.UsuarioUtil;

@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {
	private static final long serialVersionUID = -5041184007100333479L;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		usuario = UsuarioUtil.getUsuarioAutenticado();
	}

	public String getNombreCompletoUsuario() {
		return UsuarioUtil.getNombreCompletoUsuario(usuario);
	}

	public String getPerfil() {
		List<String> roles = usuario.getRoles().stream().map(Rol::getNombre).collect(Collectors.toList());
		if (roles.contains(RolConstants.ROLE_ADMIN)) {
			return "Administrador";
		} else if (roles.contains(RolConstants.ROLE_WEB_MASTER)) {
			return "Master";
                } else if (roles.contains(RolConstants.ROLE_CALL_CENTER)) {
			return "Supervisor";
                } else if (roles.contains(RolConstants.ROLE_SUPERVISOR)) {
			return "Callcenter";
                } else if (roles.contains(RolConstants.ROLE_EJECUTOR)) {
			return "Ejecutor";
                } else if (roles.contains(RolConstants.ROLE_INFRAESTRUCTURA)) {
			return "Infraestructura";
                } else {
			return "";
		}
	}
}
