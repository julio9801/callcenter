//****************************************************************************//
// @file ParametroConfiguracion.java
// 
// @description No se conoce el uso de este documento
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "a_parametro_configuracion")
public class ParametroConfiguracion implements Serializable {

	private static final long serialVersionUID = 2648237712999494986L;

	@Id
	@Getter
	@Setter
	private String key;

	@Getter
	@Setter
	private String value;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ParametroConfiguracion other = (ParametroConfiguracion) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
