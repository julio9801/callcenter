//****************************************************************************//
// @file Menu.java
// 
// @description entidad para tabla a_menu
// 
// $=> \d a_menu;
// Table “public.a_menu”
// Column             | Type                         |
// -------------------+------------------------------+
// id                 | integer                      |
// titulo             | text                         |
// url                | text                         |
// id_padre           | integer                      |
// icon               | text                         |
// -------------------+------------------------------+
// 
// @author Gerardo Blanco
// @date 14/01/2019
// @date 15/01/2019
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "a_menu")
public class Menu {

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    private Long id;
    @Getter
    @Setter
    @Column(name = "titulo")
    private String titulo;
    @Getter
    @Setter
    @Column(name = "url")
    private String url;
    @Getter
    @Setter
    @Column(name = "icon")
    private String icon;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_padre")
    private Menu menuPadre;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "r_menu_rol", joinColumns = {
        @JoinColumn(name = "ID_MENU", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "id")})
    private Set<Rol> roles = new LinkedHashSet<>();

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
        if (getClass() != obj.getClass()) {
            return false;
        }
        Menu other = (Menu) obj;
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
