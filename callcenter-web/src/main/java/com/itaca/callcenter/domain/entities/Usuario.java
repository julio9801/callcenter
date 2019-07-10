//****************************************************************************//
// @file Usuario.java
// 
// @description entidad para tabla k_usuario
// 
// $=> \d k_usuario;
// Table “public.k_usuario”
// Column             | Type                         |
// -------------------+------------------------------+
// id                 | integer                      |
// NOMBRES            | text                         |
// APELLIDO_PATERNO   | text                         |
// APELLIDO_MATERNO   | text                         |
// PASSWORD           | text                         |
// ACTIVO             | boolean                      |
// VERIFICADO         | boolean                      |
// -------------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 14/01/2019
// @date 15/01/2019
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "K_USUARIO")
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "UsuarioValueMapping", classes = @ConstructorResult(targetClass = UsuarioValue.class, columns = {
        @ColumnResult(name = "id", type = Integer.class)
        ,
        @ColumnResult(name = "nombre", type = String.class)}))})
@Getter
@Setter
public class Usuario {

    private static final long serialVersionUID = -8298432799346322646L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVO")
    private boolean activo;

    @Column(name = "VERIFICADO")
    private boolean verificado;

    @Getter
    @Setter
    @JoinColumn(name = "id_region", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_region region;

    @Getter
    @Setter
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private K_sucursal sucursal;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "R_USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "ID")})
    private List<Rol> roles;

    @Column(name = "EMAIL")
    private String email;

    public String getFullName() {
        final StringBuilder fullname = new StringBuilder(nombres);
        fullname.append(" ").append(apellidoPaterno);
        if (apellidoMaterno != null) {
            fullname.append(" ").append(apellidoMaterno);
        }

        return fullname.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
