package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.Documentos;
import com.itaca.callcenter.domain.entities.K_correo;
import com.itaca.callcenter.domain.entities.Respuesta;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.DocumentoService;
import com.itaca.callcenter.services.RespuestaService;
import com.itaca.callcenter.services.SenderService;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.StringUtils;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@ViewScoped
public class DocumentoBean implements Serializable {

    private static final Logger logger = LogManager.getLogger(UsuarioBean.class);
    private static final long serialVersionUID = -7820209981001289767L;

    //Services
    @EJB
    private RespuestaService respuestaService;
    @EJB
    private DocumentoService documentoService;
    @EJB
    private SenderService senderService;

    //Variables
    @Getter
    @Setter
    private String documento;
    
    @Getter
    @Setter
    private Integer role;

    //Methods
    @PostConstruct
    public void init() {
        logger.info("Ejecutando Documentos init()");
        documento = FacesUtil.getRequestParameter("idDocumento");

        if (StringUtils.isNotBlank(documento)) {
            System.out.print(documento);
            Usuario user = UsuarioUtil.getUsuarioAutenticado();
            role = user.getRoles().get(0).getId().intValue();
        }
    }

    public void downloadFile() throws IOException {
        System.out.print(documento);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.reset();
        response.setHeader("Content-Type", "application/octet-stream");
        OutputStream responseOutputStream = response.getOutputStream();
        URL url = new URL("file:///home/cobranza/callcenterArchive/quejas/" + documento);
        InputStream pdfInputStream = url.openStream();
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
        responseOutputStream.flush();
        pdfInputStream.close();
        responseOutputStream.close();
        facesContext.responseComplete();
    }

}
