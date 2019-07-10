//****************************************************************************//
// @file UsuarioValue.java
// 
// @description No se conoce el uso de este documento
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.io.Serializable;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioValue implements Serializable {
	private static final long serialVersionUID = -3738116843318186991L;
	private Long id;
	private String email;
	private String nombre;

	public UsuarioValue() {

	}

	public UsuarioValue(BigInteger id, String username, String nombre) {
		this.id = id.longValue();
		this.email = username;
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioValue other = (UsuarioValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
