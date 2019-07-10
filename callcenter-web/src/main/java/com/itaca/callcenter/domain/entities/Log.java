//****************************************************************************//
// @file Log.java
// 
// @description entidad para tabla zt_LOGS
// 
// $=> \d t_QUEJAS;
// Table “public.zt_logs”
// Column             | Type                         |
// -------------------+------------------------------+
// id                 | integer                      |
// fecha              | date                         |
// id_registro        | integer                      |
// tabla              | text                         |
// accion             | text                         |
// id_usuario         | integer(foreign key)         |
// -------------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 04/01/2019
// @date 15/01/2019
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "zt_logs")
@XmlRootElement
public class Log implements Serializable {

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
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_registro")
    private int idElement;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "tabla")
    private String objeto;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "accion")
    private String accion;
    @Getter
    @Setter
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }

    public Log(Integer id, Date fecha, int idElement) {
        this.id = id;
        this.fecha = fecha;
        this.idElement = idElement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.Log[ id=" + id + " ]";
    }

}
