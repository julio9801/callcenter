//****************************************************************************//
// @file TipoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo Usuario
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.domain.entities.UsuarioValue;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario> {
	public UsuarioDAO() {
		super(Usuario.class);
	}

	

        public Usuario getByUserEmail(String userName) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Usuario> criteria = criteriaBuilder.createQuery(Usuario.class);
		final Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(root).where(criteriaBuilder.equal(root.get("email"), userName));
		final List<Usuario> usuarios = entityManager.createQuery(criteria).getResultList();
		if (!usuarios.isEmpty()) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

        
        
	public List<Usuario> findAllActivesExceptAdmin() {
		final TypedQuery<Usuario> query = createAllActivesExceptAdminQuery();
		return query.getResultList();
	}

	private TypedQuery<Usuario> createAllActivesExceptAdminQuery() {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Usuario> criteria = criteriaBuilder.createQuery(Usuario.class);
		final Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(root).where(criteriaBuilder.notEqual(root.get("email"), "admin"),
				criteriaBuilder.equal(root.get("activo"), true));
		return entityManager.createQuery(criteria);
	}

	public List<Usuario> findAllActivesExceptAdmin(int first, int maxResults) {
		final TypedQuery<Usuario> query = createAllActivesExceptAdminQuery();
		query.setFirstResult(first);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public int countAllActivesExceptAdmin() {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
		final Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(criteriaBuilder.count(root)).where(criteriaBuilder.notEqual(root.get("email"), "admin"),
				criteriaBuilder.equal(root.get("activo"), true));
		final TypedQuery<Long> query = entityManager.createQuery(criteria);
		return query.getSingleResult().intValue();
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioValue> findAsValuesByRol(String rol) {
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(
				"SELECT u.id, u.email as email, u.nombres || ' ' || u.apellido_paterno || ' ' || u.apellido_materno as nombre ")
				.append("from k_usuario u inner join r_usuario_rol ur on ur.id_usuario = u.id ")
				.append("inner join c_rol r on r.id = ur.id_rol where u.activo = true and r.nombre = ?1");
		final Query query = entityManager.createNativeQuery(sqlQuery.toString(), "UsuarioValueMapping");
		query.setParameter(1, rol);
		return query.getResultList();
	}
	
	public List<Usuario> getUsuarioByEmisor(Long emisorId) {
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(
				"SELECT u from Usuario u where u.activo = true and u.emisor.id = ?1");
		final TypedQuery<Usuario> query = entityManager.createQuery(sqlQuery.toString(), Usuario.class);
		query.setParameter(1, emisorId);
		return query.getResultList();
	}
	
	
}
