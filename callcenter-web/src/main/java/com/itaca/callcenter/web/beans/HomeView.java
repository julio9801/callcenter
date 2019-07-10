package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.web.utils.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@ViewScoped
public class HomeView implements Serializable {

	private static final long serialVersionUID = 8518019008524007840L;
	private static final Logger logger = LogManager.getLogger(HomeView.class);

	@Setter
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@Getter
	private String nombreCompletoUsuario;
	@Getter
	private String perfil;

	@PostConstruct
	public void init() {
		nombreCompletoUsuario = sessionBean.getNombreCompletoUsuario();
		perfil = sessionBean.getPerfil();
	}

	public void loadNotifications() {
		try {
			
		} catch (Exception e) {
			logger.error("Error al obtener al cargar los mensajes de notificacion.", e);
			FacesUtil.addErrorMessage("Error al obtener al cargar los mensajes de notificacion.");
		}

	}

	private void showMessages(List<String> messages) {
		messages.forEach(m -> FacesUtil.addMessage(m));
	}

}
