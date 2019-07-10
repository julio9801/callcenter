package com.itaca.callcenter.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.itaca.callcenter.domain.entities.Menu;
import com.itaca.callcenter.services.MenuService;
import com.itaca.callcenter.web.utils.FacesUtil;

@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = -7820209981001289763L;

	@EJB
	private MenuService menuService;

	public MenuModel model;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		List<String> roles = (List<String>) FacesUtil.getFromSession("roles");
		model = new DefaultMenuModel();
		Set<Menu> menus;
		if (roles != null) {
			for (String rolStr : roles) {
				menus = menuService.getMenuByRol(rolStr);
				for (Menu menu : menus) {
					if (menu.getMenuPadre() == null) {
						Long idMenu = menu.getId();
						DefaultSubMenu submenu = new DefaultSubMenu(menu.getTitulo());
						for (Menu menuHijo : menus) {
							if (menuHijo.getMenuPadre() != null) {
								if (menuHijo.getMenuPadre().getId() == idMenu) {
									DefaultMenuItem item = new DefaultMenuItem(menuHijo.getTitulo());
									item.setUrl(menuHijo.getUrl() + "?faces-redirect=true");
                                                                        item.setIcon("prueba");
									submenu.addElement(item);
								}
							}
						}
                                                submenu.setIcon("icon-"+ submenu.getIcon() );
						this.model.addElement(submenu);
					}
				}
			}
			DefaultMenuItem itemSalir = new DefaultMenuItem("Salir");
			itemSalir.setUrl("/pages/logout.xhtml?faces-redirect=true");
			itemSalir.setIcon("ui-icon-extlink");
			this.model.addElement(itemSalir);
		}
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

}
