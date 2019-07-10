package com.itaca.callcenter.spring.beans;

import com.itaca.callcenter.domain.entities.Rol;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getByEmail(userName);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado.");
		}
		return new CustomUserDetails(usuario.getEmail(), usuario.getPassword(), usuario.isActivo(), true, true,
				usuario.isVerificado(), getGrantedAuthorities(usuario), usuario);
	}

	private List<GrantedAuthority> getGrantedAuthorities(Usuario usario) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Rol rol : usario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		return authorities;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
