//****************************************************************************//
// @file Queja.java
// 
// @description entidad para tabla t_QUEJAS
// 
// $=> \d t_QUEJAS;
// Table “public.t_quejas”
// Column             | Type                         |
// -------------------+------------------------------+
// id                 | integer                      |
// fecha_registro     | timestamp                    |
// fecha_cambio_estado| timestamp                    |
// estatus            | text                         |
// numero_grupo       | numeric(10,0)                |
// nombre_grupo       | text                         |
// nombre_cliente     | text                         |
// telefono           | character varying(15)        |
// nombre_reportado   | text                         |
// observacion        | text                         |
// id_tipo_reportador | integer(foreign key)         |
// id_puesto_reportado| integer(foreign key)         |
// id_correo          | integer(foreign key)         |
// id_causa           | integer(foreign key)         |
// id_sucursal        | integer(foreign key)         |
// id_usuario         | integer(foreign key)         |
// -------------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 10/01/2019
// @date 15/01/2019
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_quejas")
@XmlRootElement
public class Queja implements Serializable {
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
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registro;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cambio_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cambio;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "estatus")
    private String estatus;
    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "numero_grupo")
    private BigDecimal numero;
    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "nombre_grupo")
    private String grupo;
    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "nombre_cliente")
    private String cliente;
    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre_reportado")
    private String reportado;
    @Getter
    @Setter
    @Basic(optional = false)
    @NotNull
    @Column(name = "observacion")
    private String observacion;
    @Getter
    @Setter
    @JoinColumn(name = "id_tipo_reportador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_reportador reportador;
    @Getter
    @Setter
    @JoinColumn(name = "id_puesto_reportado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_puesto puesto;
    @Getter
    @Setter
    @JoinColumn(name = "id_correo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_correo correo;
    @Getter
    @Setter
    @JoinColumn(name = "id_causa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_causa causa;
    @Getter
    @Setter
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_sucursal sucursal;
    @Getter
    @Setter
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    //Relacion externa
    @OneToMany(mappedBy = "queja")
    private List<Respuesta> respuestas;

    private transient String producto;
    
    private transient String causaCarga;
    private transient String sucursalCarga;
    private transient String reportadorCarga;
    private transient String puestoCarga;
    private transient String correoCarga;
    
    public Queja() {
    }

    public Queja(Integer id) {
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
        if (!(object instanceof Queja)) {
            return false;
        }
        Queja other = (Queja) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "itaca.com.mx.domain.Queja[ id=" + id + " ]";
    }

}
