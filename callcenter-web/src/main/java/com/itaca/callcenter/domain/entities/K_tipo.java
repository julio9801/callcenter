//****************************************************************************//
// @file K_tipo.java
// 
// @description entidad para tabla k_TIPOS
// 
// $=> \d k_TIPOS;
// Table “public.k_tipos”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | bigint                       |
// numero          | bigint                       |
// tipo            | character varying(100)       |
// ----------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 09/01/2019
// @date 15/01/2019
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "k_tipos")
@XmlRootElement
public class K_tipo implements Serializable {

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
    @Column(name = "numero")
    private BigDecimal numero;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private String tipo;

    public K_tipo() {
    }

    public K_tipo(Integer id) {
        this.id = id;
    }

    public K_tipo(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof K_tipo)) {
            return false;
        }
        K_tipo other = (K_tipo) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_tipo[ id=" + id + " ]";
    }
}
