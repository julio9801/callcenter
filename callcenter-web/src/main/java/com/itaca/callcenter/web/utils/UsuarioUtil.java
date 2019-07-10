//****************************************************************************//
// @file UsuarioUtil.java
//
// @description Documento no creado por el desarrollador original. Se modifica 
// para agregar roles disponibles. Contien utilidades para manejo de usuarios
//
// @author Gerardo Blanco
// @date 10/01/2019
// @date 11/01/2019
//****************************************************************************//
package com.itaca.callcenter.web.utils;

import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.spring.beans.CustomUserDetails;
import javax.faces.context.FacesContext;
import org.springframework.security.core.context.SecurityContextHolder;


public final class UsuarioUtil {
	private UsuarioUtil() {

	}

	public static Usuario getUsuarioAutenticado() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails != null && userDetails instanceof CustomUserDetails) {
			return ((CustomUserDetails) userDetails).getUsuario();
		} else {
			return null;
		}
	}

	public static String getNombreCompletoUsuario(Usuario usuario) {
		if (usuario == null) {
			return "";
		} else {
			String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidoPaterno();
			if (StringUtils.isNotBlank(usuario.getApellidoMaterno())) {
				nombreCompleto = nombreCompleto + " " + usuario.getApellidoMaterno();
			}
			return nombreCompleto;
		}
	}

	public static boolean isAdmin() {
		return isUserinRole(RolConstants.ROLE_ADMIN);
	}

	public static boolean isMaster() {
		return isUserinRole(RolConstants.ROLE_WEB_MASTER);
	}
        
        public static boolean isSupervisor() {
		return isUserinRole(RolConstants.ROLE_SUPERVISOR);
	}

        public static boolean isCallcenter() {
		return isUserinRole(RolConstants.ROLE_CALL_CENTER);
	}
        
        public static boolean iseEjecutor() {
		return isUserinRole(RolConstants.ROLE_EJECUTOR);
	}
	
        public static boolean isInfraestructura() {
		return isUserinRole(RolConstants.ROLE_INFRAESTRUCTURA);
	}
        
	public static boolean isUserinRole(String role) {
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
	}
}

