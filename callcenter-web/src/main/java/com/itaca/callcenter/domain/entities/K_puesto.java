//****************************************************************************//
// @file K_puesto.java
// 
// @description entidad para tabla k_PUESTOS_REPORTADOS
// 
// $=> \d k_PUESTOS_REPORTADOS;
// Table “public.k_puestos_reportados”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | bigint                       |
// puesto          | character varying(100)       |
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
@Table(name = "k_puestos_reportados")
@XmlRootElement
public class K_puesto implements Serializable {

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
    @Column(name = "puesto")
    private String puesto;

    public K_puesto() {
    }

    public K_puesto(Integer id) {
        this.id = id;
    }

    public K_puesto(Integer id, String puesto) {
        this.id = id;
        this.puesto = puesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof K_puesto)) {
            return false;
        }
        K_puesto other = (K_puesto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_puesto[ id=" + id + " ]";
    }

}
