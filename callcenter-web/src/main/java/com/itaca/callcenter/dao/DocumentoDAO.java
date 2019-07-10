//****************************************************************************//
// @file DocumentoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo Documentos
//
// @author Gerardo Blanco
// @date 22/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.Documentos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class DocumentoDAO extends GenericDAO<Documentos> {
    public DocumentoDAO(){
        super(Documentos.class);
    }
   
    public Documentos findById(Integer id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Documentos> criteria = criteriaBuilder.createQuery(Documentos.class);
        final Root<Documentos> root = criteria.from(Documentos.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        final List<Documentos> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<Documentos> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Documentos> criteria = criteriaBuilder.createQuery(Documentos.class);
        final Root<Documentos> root = criteria.from(Documentos.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<Documentos> findAll(int first, int maxResults) {
        final TypedQuery<Documentos> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public List<Documentos> findAllByQueja(Integer id) {
      final StringBuilder sqlQuery = new StringBuilder(
                "select d from Documentos d join fetch d.queja q ");
        sqlQuery.append("where q.id = :id");
        
        final TypedQuery<Documentos> query = entityManager.createQuery(sqlQuery.toString(),
                Documentos.class);
        
        query.setParameter("id", id);
        query.setMaxResults(0);
        
        final List<Documentos> results = query.getResultList();
        return results;
    }
}
