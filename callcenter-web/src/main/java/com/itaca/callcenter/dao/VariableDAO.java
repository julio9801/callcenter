//****************************************************************************//
// @file VariableDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_variable
//
// @author Gerardo Blanco
// @date 27/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_variable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class VariableDAO extends GenericDAO<K_variable> {
    public VariableDAO(){
        super(K_variable.class);
    }
   
    public K_variable findById(String id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_variable> criteria = criteriaBuilder.createQuery(K_variable.class);
        final Root<K_variable> root = criteria.from(K_variable.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        final List<K_variable> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    public K_variable findByValor(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_variable> criteria = criteriaBuilder.createQuery(K_variable.class);
        final Root<K_variable> root = criteria.from(K_variable.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("valor"), str));
        final List<K_variable> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<K_variable> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_variable> criteria = criteriaBuilder.createQuery(K_variable.class);
        final Root<K_variable> root = criteria.from(K_variable.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_variable> findAll(int first, int maxResults) {
        final TypedQuery<K_variable> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_variable> root = criteria.from(K_variable.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
