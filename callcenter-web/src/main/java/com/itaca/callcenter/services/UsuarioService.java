package com.itaca.callcenter.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ejb.Local;

import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.domain.entities.UsuarioValue;
import com.itaca.callcenter.services.pojo.UserValidation;

@Local
public interface UsuarioService {

	public List<Usuario> getAll();

	public void save(Usuario usuario, Usuario usuarioLogueado);

	public int countAll();

	public List<Usuario> getResultList(int first, int pageSize);

	
	
	public UserValidation validateSetPasswordToken(String token)
			throws IllegalArgumentException, UnsupportedEncodingException;

    public Usuario getByEmail(String email);

}
