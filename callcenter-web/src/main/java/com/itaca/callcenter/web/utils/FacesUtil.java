package com.itaca.callcenter.web.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public final class FacesUtil {
	private final static Log log = LogFactory.getLog(FacesUtil.class);

	public static void addMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}

	public static String getSavedUrl() {
		HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest());

		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request,
				(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse());

		if (savedRequest != null) {
			try {
				URL url = new URL(savedRequest.getRedirectUrl());
                                System.out.println(url);
				log.info("Redirect URL-->: " + url.toString());
				return url.getFile().substring(request.getContextPath().length());
			} catch (Exception e) {
				log.error(e.getMessage() + " Using default URL");
			}
		}
		return "/pages/home.xhtml?faces-redirect=true";
	}
        

	public static void addErrorMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));

	}
        
        public static String getQuejaUrl(String param,String str) {
		return "/pages/quejas/consultaQueja.xhtml?faces-redirect=true&id="+param+"&mensaje="+str;
	}

	public static void addErrorMessage(String msg, String target) {
		FacesContext.getCurrentInstance().addMessage(target, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));

	}

	public static void putInSession(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	public static Object getFromSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public static boolean existInSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(key);
	}

	public static Object removeFromSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	public static String getRequestParameter(String key) {
		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		return parameterMap.get(key);
	}
        
        public static String getUrl(){
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String uri = request.getRequestURI();
            return uri;
        }
        
}
