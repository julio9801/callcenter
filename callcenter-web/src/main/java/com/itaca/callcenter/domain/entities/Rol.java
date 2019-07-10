//****************************************************************************//
// @file Usuario.java
// 
// @description entidad para tabla r_usuario_rol
// 
// @author Gerardo Blanco
// @date 14/01/2019
//****************************************************************************//
package com.itaca.callcenter.domain.entities;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "C_ROL")
public class Rol extends CatalogEntity {

    private static final long serialVersionUID = -5117746047870601603L;

    @ManyToMany
    @JoinTable(name = "r_menu_rol", joinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_MENU", referencedColumnName = "id")})
    private Set<Menu> menus = new LinkedHashSet<>();

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

}
