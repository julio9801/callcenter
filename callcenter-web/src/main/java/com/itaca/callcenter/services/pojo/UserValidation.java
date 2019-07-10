package com.itaca.callcenter.services.pojo;

import com.itaca.callcenter.domain.entities.Usuario;

public class UserValidation {
	private Usuario user;
	private String errorMessage;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
