//****************************************************************************//
// @file K_sucursal.java
// 
// @description entidad para tabla k_SUCURSALES
// 
// $=> \d k_SUCURSALES;
// Table “public.k_sucursal”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | bigint                       |
// sucursal        | character varying(100)       |
// ----------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 09/01/2019
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "k_sucursales")
@XmlRootElement
public class K_sucursal implements Serializable {
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
    @Column(name = "sucursal")
    private String sucursal;
    @Getter
    @Setter
    @JoinColumn(name = "id_region", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_region idregion;

    public K_sucursal() {
    }

    public K_sucursal(Integer id) {
        this.id = id;
    }

    public K_sucursal(Integer id, String sucursal) {
        this.id = id;
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof K_sucursal)) {
            return false;
        }
        K_sucursal other = (K_sucursal) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_sucursal[ id=" + id + " ]";
    }
}
