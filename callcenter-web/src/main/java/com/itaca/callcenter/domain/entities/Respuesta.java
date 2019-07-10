//****************************************************************************//
// @file Respuesta.java
// 
// @description entidad para tabla t_RESPUESTAS
// 
// $=> \d t_RESPUESTAS;
// Table “public.t_respuestas”
// Column          | Type                         |
// ----------------+------------------------------+
// id              | integer                      |
// respuesta       | text                         |
// nombre_responde | text                         |
// fecha           | date                         |
// id_usuario      | integer(foreign key)         |
// id_queja        | integer(foreign key)         |
// ----------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 09/01/2019
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
@Table(name = "t_respuestas")
@XmlRootElement
public class Respuesta implements Serializable {
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
    @Column(name = "respuesta")
    private String respuesta;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre_responde")
    private String responde;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Getter
    @Setter
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Getter
    @Setter
    @JoinColumn(name = "id_queja", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Queja queja;
    
    public Respuesta() {
    }

    public Respuesta(Integer id) {
        this.id = id;
    }

    public Respuesta(Integer id, String respuesta, String responde, Date fecha, Usuario usuario) {
        this.id = id;
        this.respuesta = respuesta;
        this.responde = responde;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.Respuesta[ id=" + id + " ]";
    }
    
}
