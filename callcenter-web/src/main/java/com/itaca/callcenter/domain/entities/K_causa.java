//****************************************************************************//
// @file K_causa.java
// 
// @description entidad para tabla k_TIPOS
// 
// $=> \d k_CAUSAS;
// Table “public.k_causas”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | bigint                       |
// causa           | character varying(100)       |
// numero          | bigint                       |
// id_tipo         | integer(foreign key)         |
// ----------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 07/01/2019
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "k_causas")
@XmlRootElement
public class K_causa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Getter
    @Setter
    @NotNull
    @Column(name = "causa")
    private String causa;
    @Basic(optional = false)
    @Getter
    @Setter
    @NotNull
    @Column(name = "numero")
    private BigDecimal numero;
    @Getter
    @Setter
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_tipo tipo;

    public K_causa() {
    }

    public K_causa(Integer id) {
        this.id = id;
    }

    public K_causa(Integer id, String causa) {
        this.id = id;
        this.causa= causa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof K_causa)) {
            return false;
        }
        K_causa other = (K_causa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_causa[ id=" + id + " ]";
    }
    
}
