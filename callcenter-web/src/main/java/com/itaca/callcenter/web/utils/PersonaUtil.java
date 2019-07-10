package com.itaca.callcenter.web.utils;

public final class PersonaUtil {
	private PersonaUtil() {

	}

	public static String getNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) {
		StringBuffer nombreCompleto = new StringBuffer(nombre);
		nombreCompleto.append(" ").append(apellidoPaterno);
		if (StringUtils.isNotBlank(apellidoMaterno)) {
			nombreCompleto.append(" ").append(apellidoMaterno);
		}
		return nombreCompleto.toString();
	}
}
