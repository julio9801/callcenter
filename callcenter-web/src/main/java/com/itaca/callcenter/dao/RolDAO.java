//****************************************************************************//
// @file CorreoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo ROL
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import com.itaca.callcenter.domain.entities.Rol;

@Stateless
public class RolDAO extends GenericDAO<Rol> {
	public RolDAO() {
		super(Rol.class);
	}
	
	public Rol findByName(String nombre) {
		final StringBuilder sqlQuery = new StringBuilder("select r from Rol r where r.nombre = :nombre");
		final TypedQuery<Rol> query = entityManager.createQuery(sqlQuery.toString(), Rol.class);
		query.setParameter("nombre", nombre);
		query.setMaxResults(0);
		final List<Rol> results = query.getResultList();
		
		if (results == null || results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}
}
