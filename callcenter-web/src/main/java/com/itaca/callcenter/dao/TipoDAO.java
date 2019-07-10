//****************************************************************************//
// @file TipoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_tipo
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_tipo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class TipoDAO extends GenericDAO<K_tipo> {
    public TipoDAO(){
        super(K_tipo.class);
    }
   
    public K_tipo findByTipo(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_tipo> criteria = criteriaBuilder.createQuery(K_tipo.class);
        final Root<K_tipo> root = criteria.from(K_tipo.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("tipo"), str));
        final List<K_tipo> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<K_tipo> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_tipo> criteria = criteriaBuilder.createQuery(K_tipo.class);
        final Root<K_tipo> root = criteria.from(K_tipo.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_tipo> findAll(int first, int maxResults) {
        final TypedQuery<K_tipo> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_tipo> root = criteria.from(K_tipo.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
