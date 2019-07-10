//****************************************************************************//
// @file K_correo.java
// 
// @description entidad para tabla k_CORREOS
// 
// $=> \d k_CORREOS;
// Table “public.k_correos”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | bigint                       |
// correo          | character varying(100)       |
// id_area         | integer(foreign key)         |
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "k_correos")
@XmlRootElement
public class K_correo implements Serializable {
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
    @Column(name = "correo")
    private String correo;
    @Getter
    @Setter
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_area area;

    public K_correo() {
    }

    public K_correo(Integer id) {
        this.id = id;
    }

    public K_correo(Integer id, String correo) {
        this.id = id;
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof K_correo)) {
            return false;
        }
        K_correo other = (K_correo) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.K_correo[ id=" + id + " ]";
    }
    
}
