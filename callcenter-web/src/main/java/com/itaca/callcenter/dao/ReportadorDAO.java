//****************************************************************************//
// @file ReportadorDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo K_reportador
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_reportador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class ReportadorDAO extends GenericDAO<K_reportador> {
    public ReportadorDAO(){
        super(K_reportador.class);
    }
   
        public K_reportador findByReportador(String str) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_reportador> criteria = criteriaBuilder.createQuery(K_reportador.class);
        final Root<K_reportador> root = criteria.from(K_reportador.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("tipo"), str));
        final List<K_reportador> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<K_reportador> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_reportador> criteria = criteriaBuilder.createQuery(K_reportador.class);
        final Root<K_reportador> root = criteria.from(K_reportador.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<K_reportador> findAll(int first, int maxResults) {
        final TypedQuery<K_reportador> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<K_reportador> root = criteria.from(K_reportador.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
}
