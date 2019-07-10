//****************************************************************************//
// @file CorreoDAO.java
// 
// @description Queries persistentes a la BD con objetos de tipo Queja
//
// @author Gerardo Blanco
// @date 22/12/2018
//****************************************************************************//

package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.Queja;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class QuejaDAO extends GenericDAO<Queja> {
    public QuejaDAO(){
        super(Queja.class);
    }
   
    public Queja findById(Integer id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Queja> criteria = criteriaBuilder.createQuery(Queja.class);
        final Root<Queja> root = criteria.from(Queja.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        final List<Queja> cargos = entityManager.createQuery(criteria).getResultList();
        if (!cargos.isEmpty()) {
            return cargos.get(0);
        } else {
            return null;
        }
    }
    
    private TypedQuery<Queja> createAllQuery() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Queja> criteria = criteriaBuilder.createQuery(Queja.class);
        final Root<Queja> root = criteria.from(Queja.class);
        criteria.select(root);
        return entityManager.createQuery(criteria);
    }
    
    public List<Queja> findAll(int first, int maxResults) {
        final TypedQuery<Queja> query = createAllQuery();
        query.setFirstResult(first);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
    
    public int countAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        final Root<Queja> root = criteria.from(Queja.class);
        criteria.select(criteriaBuilder.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(criteria);
        return query.getSingleResult().intValue();
    } 
    
    public List<Queja> findAllByUsuario(Integer id) {
      final StringBuilder sqlQuery = new StringBuilder(
                "select q from Queja q join fetch q.usuario u ");
        sqlQuery.append("where u.id = :id");
        
        final TypedQuery<Queja> query = entityManager.createQuery(sqlQuery.toString(),
                Queja.class);
        
        query.setParameter("id", id.longValue());
        query.setMaxResults(0);
        
        final List<Queja> results = query.getResultList();
        return results;
    }
    
    public List<Queja> findAllByCorreoAndActivo(String str) {
      final StringBuilder sqlQuery = new StringBuilder(
                "select q from Queja q "
                        + "join fetch q.correo c ");
        sqlQuery.append("where c.correo = :str");
        
        final TypedQuery<Queja> query = entityManager.createQuery(sqlQuery.toString(),
                Queja.class);
        
        query.setParameter("str", str);
        query.setMaxResults(0);
        
        final List<Queja> results = query.getResultList();
        return results;
    }
}
