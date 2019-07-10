//****************************************************************************//
// @file SucursalDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_sucursal
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_sucursal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class SucursalDAO extends GenericDAO<K_sucursal> {
    public SucursalDAO(){
        super(K_sucursal.class);
    }
   
    public K_sucursal findBySucursal(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_sucursal> criteria = criteriaBuilder.createQuery(K_sucursal.class);
        final Root<K_sucursal> root = criteria.from(K_sucursal.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("sucursal"), str));
        final List<K_sucursal> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<K_sucursal> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_sucursal> criteria = criteriaBuilder.createQuery(K_sucursal.class);
        final Root<K_sucursal> root = criteria.from(K_sucursal.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_sucursal> findAll(int first, int maxResults) {
        final TypedQuery<K_sucursal> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_sucursal> root = criteria.from(K_sucursal.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
