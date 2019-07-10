//****************************************************************************//
// @file GenericDAO.java
// 
// @description No se conoce el uso de este documento
//****************************************************************************//
package com.itaca.callcenter.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;



public abstract class GenericDAO<T> {
    public static final String PERSISTENCE_UNIT_NAME = "EJBCallcenterPU";
	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
	protected EntityManager entityManager;
	private Class<T> entityClass;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public T persist(T entity) {
		this.entityManager.persist(entity);
		return entity;
	}
	
	public T merge(T entity) {
		this.entityManager.merge(entity);
		return entity;
	}

	public T find(Object id) {
		return this.entityManager.find(entityClass, id);
	}

	public T remove(T entity) {
		this.entityManager.remove(entity);
		return entity;
	}
	
	public T removeMerge(T entity) {
		this.entityManager.remove(entityManager.merge(entity));
		return entity;
	}
	
	public T update(T entity) {
		this.entityManager.merge(entity);
		return entity;
	}

	public List<T> findAll() {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> q = cb.createQuery(entityClass);
		final Root<T> c = q.from(entityClass);
		q.select(c);
		q.orderBy(cb.asc(c.get("id")));
		return entityManager.createQuery(q).getResultList();
	}
      

	public List<T> find(int firstResult, int maxResults) {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> q = cb.createQuery(entityClass);
		final Root<T> c = q.from(entityClass);
		q.select(c);
		q.orderBy(cb.asc(c.get("id")));
		final TypedQuery<T> query = entityManager.createQuery(q);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public int count() {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> q = cb.createQuery(Long.class);
		final Root<T> c = q.from(entityClass);
		q.select(cb.count(c));
		final TypedQuery<Long> query = entityManager.createQuery(q);
		return query.getSingleResult().intValue();
	}

	public T findByField(String field, String value) {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> q = cb.createQuery(entityClass);
		final Root<T> c = q.from(entityClass);
		q.select(c);
		ParameterExpression<String> tipoValue = cb.parameter(String.class);
		q.where(cb.equal(c.get(field), tipoValue));
		TypedQuery<T> query = entityManager.createQuery(q);
		query.setParameter(tipoValue, value);
		return getSingleResultOrNull(query);
	}
	
	public T findByField(String field, Long value) { //TODO
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> q = cb.createQuery(entityClass);
		final Root<T> c = q.from(entityClass);
		q.select(c);
		ParameterExpression<Long> tipoValue = cb.parameter(Long.class);
		q.where(cb.equal(c.get(field), tipoValue));
		TypedQuery<T> query = entityManager.createQuery(q);
		query.setParameter(tipoValue, value);
		return getSingleResultOrNull(query);
	}
	
	public T getSingleResultOrNull(TypedQuery<T> query) {
		final List<T> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

}
