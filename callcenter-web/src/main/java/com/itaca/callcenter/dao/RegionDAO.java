package com.itaca.callcenter.dao;

import com.itaca.callcenter.domain.entities.K_region;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class RegionDAO extends GenericDAO<K_region> {
    
    public RegionDAO() {
        super(K_region.class);
    }
    
    @Override
    public List<K_region> findAll() {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K_region> q = cb.createQuery(K_region.class);
        final Root<K_region> c = q.from(K_region.class);
        q.select(c);
        q.orderBy(cb.asc(c.get("idregion")));
        return entityManager.createQuery(q).getResultList();
    }
    
    public K_region findById(Integer id) {
        final StringBuilder sqlQuery = new StringBuilder(
                "select r from IcRegiones r ");
        sqlQuery.append("where r.idregion = :id");

        final TypedQuery<K_region> query = entityManager.createQuery(sqlQuery.toString(),K_region.class);

        query.setParameter("id", id);
        query.setMaxResults(0);

        final List<K_region> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    public K_region findByNombre(String nombre) {
        final StringBuilder sqlQuery = new StringBuilder(
                "select r from IcRegiones r ");
        sqlQuery.append("where UPPER(r.nombre) = :nombre");

        final TypedQuery<K_region> query = entityManager.createQuery(sqlQuery.toString(),K_region.class);

        query.setParameter("nombre", nombre.toUpperCase());
        query.setMaxResults(0);

        final List<K_region> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
}