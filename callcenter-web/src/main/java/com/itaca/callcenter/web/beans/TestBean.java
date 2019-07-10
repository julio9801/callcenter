package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Respuesta;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.QuejaService;
import com.itaca.callcenter.services.RespuestaService;
import com.itaca.callcenter.services.SenderService;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.StringUtils;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@ManagedBean
@ViewScoped
public class TestBean implements Serializable{
    private static final Logger logger = LogManager.getLogger(UsuarioBean.class);
    private static final long serialVersionUID = -7820209981001289767L;
    
    //Services
    @EJB
    private SenderService senderService;


    //Variables

    
    //Methods
    @PostConstruct
    public void init() {
        logger.info("Test init() en log");
        System.out.print("Test init() en print");
    }
    
    public void testMail(){
        logger.info("Test testMail() en log");
        System.out.print("Test testMail() en print");
        try {
            senderService.cargaTest();
        } catch (Exception e){
            System.out.print("Error->"+e.getMessage());
        }
    }
    
}
