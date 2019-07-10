package com.itaca.callcenter.spring.beans;

import com.itaca.callcenter.domain.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import javax.annotation.PostConstruct;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = -7275471720828522362L;
    private static final Logger logger = LogManager.getLogger(LoginBean.class);

    
//    @EJB 
//    LogService logService;
    
    @Inject
    private AuthenticationManager authenticationManager;

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    Usuario user;
    
    
   

 
    @PostConstruct
    public void init() {
     
    }

    @SuppressWarnings("unchecked")
    public String login() {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(this.username, this.password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
            List<String> roles = new ArrayList<>();
            for (SimpleGrantedAuthority grantedAuthority : authorities) {
                roles.add(grantedAuthority.getAuthority());
            }
            FacesUtil.putInSession("roles", roles);
            user=UsuarioUtil.getUsuarioAutenticado();
            //logService.error(user, 1, "LogIn", "Exito");
        } catch (AuthenticationException ex) {
            logger.error(ex.getMessage(), ex);
            FacesUtil.addErrorMessage(ex.getMessage());
            return "";
        }
        //System.out.println( "Esto"+FacesUtil.getSavedUrl());
        return FacesUtil.getSavedUrl() + "?faces-redirect=true";
    }

}
