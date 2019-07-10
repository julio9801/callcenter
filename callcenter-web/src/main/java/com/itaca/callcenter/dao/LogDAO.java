//****************************************************************************//
// @file LogDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo Log
//
// @author Gerardo Blanco
// @date 22/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.Log;
import javax.ejb.Stateless;


@Stateless
public class LogDAO extends GenericDAO<Log> {
	public LogDAO() {
		super(Log.class);
	}
}
