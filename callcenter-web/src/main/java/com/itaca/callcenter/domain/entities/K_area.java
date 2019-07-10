//****************************************************************************//
// @file K_area.java
// 
// @description entidad para tabla k_AREAS
// 
// $=> \d k_AREAS;
// Table “public.k_areas”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | bigint                       |
// area            | character varying(100)       |
// ----------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 10/01/2019
// @date 15/01/2019
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "k_areas")
@XmlRootElement
public class K_area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "area")
    private String area;

    public K_area() {
    }

    public K_area(Integer id) {
        this.id = id;
    }

    public K_area(Integer id, String area) {
        this.id = id;
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof K_area)) {
            return false;
        }
        K_area other = (K_area) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_area[ id=" + id + " ]";
    }

}
