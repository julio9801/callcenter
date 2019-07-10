//****************************************************************************//
// @file ParametroConfiguracionDAO.java
// 
// @description No se conoce el uso de este documento
//****************************************************************************//
package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.ParametroConfiguracion;
import javax.ejb.Stateless;

@Stateless
public class ParametroConfiguracionDAO extends GenericDAO<ParametroConfiguracion> {
	public ParametroConfiguracionDAO() {
		super(ParametroConfiguracion.class);
	}
}
