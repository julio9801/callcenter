//****************************************************************************//
// @file K_reportador.java
// 
// @description entidad para tabla k_TIPOS_REPORTADORES
// 
// $=> \d k_TIPOS_REPORTADORES;
// Table “public.k_tipos_reportadores”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | bigint                       |
// tipo            | character varying(100)       |
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "k_tipos_reportadores")
@XmlRootElement
public class K_reportador implements Serializable {
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
    @Column(name = "tipo")
    private String tipo;

    public K_reportador() {
    }

    public K_reportador(Integer id) {
        this.id = id;
    }

    public K_reportador(Integer id, String tipo) {
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
        if (!(object instanceof K_reportador)) {
            return false;
        }
        K_reportador other = (K_reportador) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_reportador[ id=" + id + " ]";
    }
}
