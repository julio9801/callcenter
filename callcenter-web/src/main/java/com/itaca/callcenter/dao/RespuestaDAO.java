//****************************************************************************//
// @file CorreoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo Respuesta
//
// @author Gerardo Blanco
// @date 23/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.Respuesta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class RespuestaDAO extends GenericDAO<Respuesta> {
    public RespuestaDAO(){
        super(Respuesta.class);
    }
   
    public Respuesta findById(Integer id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Respuesta> criteria = criteriaBuilder.createQuery(Respuesta.class);
        final Root<Respuesta> root = criteria.from(Respuesta.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        final List<Respuesta> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<Respuesta> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Respuesta> criteria = criteriaBuilder.createQuery(Respuesta.class);
        final Root<Respuesta> root = criteria.from(Respuesta.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<Respuesta> findAll(int first, int maxResults) {
        final TypedQuery<Respuesta> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<Respuesta> root = criteria.from(Respuesta.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
    
    public List<Respuesta> findAllByUsuario(Integer id) {
      final StringBuilder sqlQuery = new StringBuilder(
                "select r from Respuesta r join fetch r.usuario u ");
        sqlQuery.append("where u.id = :id");
        
        final TypedQuery<Respuesta> query = entityManager.createQuery(sqlQuery.toString(),
                Respuesta.class);
        
        query.setParameter("id", id.longValue());
        query.setMaxResults(0);
        
        final List<Respuesta> results = query.getResultList();
        return results;
    }
    
    public List<Respuesta> findAllByQueja(Integer id) {
      final StringBuilder sqlQuery = new StringBuilder(
                "select r from Respuesta r join fetch r.queja q ");
        sqlQuery.append("where q.id = :id");
        
        final TypedQuery<Respuesta> query = entityManager.createQuery(sqlQuery.toString(),
                Respuesta.class);
        
        query.setParameter("id", id);
        query.setMaxResults(0);
        
        final List<Respuesta> results = query.getResultList();
        return results;
    }
}
