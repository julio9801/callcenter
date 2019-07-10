//****************************************************************************//
// @file CausaDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_causa
//
// @author Gerardo Blanco
// @date 22/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_causa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class CausaDAO extends GenericDAO<K_causa> {
    public CausaDAO(){
        super(K_causa.class);
    }
   
    public K_causa findByCausa(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_causa> criteria = criteriaBuilder.createQuery(K_causa.class);
        final Root<K_causa> root = criteria.from(K_causa.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("causa"), str));
        final List<K_causa> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    public List<K_causa> findByTipo(int id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_causa> criteria = criteriaBuilder.createQuery(K_causa.class);
        final Root<K_causa> root = criteria.from(K_causa.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("tipo"), id));
        final List<K_causa> cargos = entityManager.createQuery(criteria).getResultList();
        return cargos;
    }
    
    private TypedQuery<K_causa> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_causa> criteria = criteriaBuilder.createQuery(K_causa.class);
        final Root<K_causa> root = criteria.from(K_causa.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_causa> findAll(int first, int maxResults) {
        final TypedQuery<K_causa> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_causa> root = criteria.from(K_causa.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
