//****************************************************************************//
// @file CorreoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_correo
//
// @author Gerardo Blanco
// @date 22/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_correo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class CorreoDAO extends GenericDAO<K_correo> {
    public CorreoDAO(){
        super(K_correo.class);
    }
   
    public K_correo findByCorreo(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_correo> criteria = criteriaBuilder.createQuery(K_correo.class);
        final Root<K_correo> root = criteria.from(K_correo.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("correo"), str));
        final List<K_correo> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<K_correo> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_correo> criteria = criteriaBuilder.createQuery(K_correo.class);
        final Root<K_correo> root = criteria.from(K_correo.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_correo> findAll(int first, int maxResults) {
        final TypedQuery<K_correo> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_correo> root = criteria.from(K_correo.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
