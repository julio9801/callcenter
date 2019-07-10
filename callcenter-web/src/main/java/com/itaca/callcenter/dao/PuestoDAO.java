//****************************************************************************//
// @file PuestoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_puesto
//
// @author Gerardo Blanco
// @date 22/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_puesto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class PuestoDAO extends GenericDAO<K_puesto> {
    public PuestoDAO(){
        super(K_puesto.class);
    }
   
    public K_puesto findByPuesto(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_puesto> criteria = criteriaBuilder.createQuery(K_puesto.class);
        final Root<K_puesto> root = criteria.from(K_puesto.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("puesto"), str));
        final List<K_puesto> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<K_puesto> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_puesto> criteria = criteriaBuilder.createQuery(K_puesto.class);
        final Root<K_puesto> root = criteria.from(K_puesto.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_puesto> findAll(int first, int maxResults) {
        final TypedQuery<K_puesto> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_puesto> root = criteria.from(K_puesto.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
