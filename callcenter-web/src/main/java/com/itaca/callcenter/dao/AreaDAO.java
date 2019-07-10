//****************************************************************************//
// @file AreaDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_area
//
// @author Gerardo Blanco
// @date 22/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_area;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class AreaDAO extends GenericDAO<K_area> {
    public AreaDAO(){
        super(K_area.class);
    }
   
    public K_area findByArea(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_area> criteria = criteriaBuilder.createQuery(K_area.class);
        final Root<K_area> root = criteria.from(K_area.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("area"), str));
        final List<K_area> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<K_area> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_area> criteria = criteriaBuilder.createQuery(K_area.class);
        final Root<K_area> root = criteria.from(K_area.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_area> findAll(int first, int maxResults) {
        final TypedQuery<K_area> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_area> root = criteria.from(K_area.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
