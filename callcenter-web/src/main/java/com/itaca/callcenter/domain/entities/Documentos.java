//****************************************************************************//
// @file Documetnos.java
// 
// @description entidad para tabla t_QUEJAS
// 
// $=> \d t_DOCUMENTOS;
// Table “public.t_documentos”
// Column             | Type                         |
// -------------------+------------------------------+
// id                 | integer                      |
// nombre             | text                         |
// id_queja           | integer(foreign key)         |
// -------------------+------------------------------+
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
@Table(name = "t_documentos")
@XmlRootElement
public class Documentos implements Serializable {

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
    @Column(name = "nombre")
    private String nombre;
    @Getter
    @Setter
    @JoinColumn(name = "id_queja", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Queja queja;

    public Documentos() {
    }

    public Documentos(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Documentos)) {
            return false;
        }
        Documentos other = (Documentos) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.Queja[ id=" + id + " ]";
    }

}
