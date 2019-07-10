//****************************************************************************//
// @file K_variable.java
// 
// @description entidad para tabla k_VARIABLES
// 
// $=> \d k_VARIABLES;
// Table “public.k_variables”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | character varying(100)       |
// variable        | character varying(100)       |
// ----------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 27/12/2018
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
@Table(name = "k_variables")
@XmlRootElement
public class K_variable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private String valor;

    public K_variable() {
    }

    public K_variable(String id) {
        this.id = id;
    }

    public K_variable(String id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof K_variable)) {
            return false;
        }
        K_variable other = (K_variable) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_variable[ id=" + id + " ]";
    }

}
