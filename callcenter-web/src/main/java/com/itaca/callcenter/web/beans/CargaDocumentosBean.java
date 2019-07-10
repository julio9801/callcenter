package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.Documentos;
import com.itaca.callcenter.domain.entities.K_causa;
import com.itaca.callcenter.domain.entities.K_tipo;
import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.CausaService;
import com.itaca.callcenter.services.DocumentoService;
import com.itaca.callcenter.services.QuejaService;
import com.itaca.callcenter.services.SenderService;
import com.itaca.callcenter.web.utils.FacesUtil;
import com.itaca.callcenter.web.utils.UsuarioUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CargaDocumentosBean implements Serializable {

    private static final long serialVersionUID = 8518019008524007840L;
    private static final Logger logger = LogManager.getLogger(CargaDocumentosBean.class);

    //Services
    @EJB
    QuejaService quejaService;
    @EJB
    CausaService causaService;
    @EJB
    DocumentoService documentoService;
    @EJB
    SenderService senderService;

    //Variables
    Usuario user;
    @Getter
    @Setter
    private String idQueja;
    @Getter
    @Setter
    private Queja queja;
    @Getter
    @Setter
    private String producto;
    @Getter
    private List<K_causa> list = new ArrayList<>();
    private UploadedFile archivo;


    //Methods
    @PostConstruct
    public void init() {
        idQueja = FacesUtil.getRequestParameter("quejaId");
        queja = quejaService.getById(Integer.valueOf(idQueja));
        System.out.print(queja);
    }

    public void guardaArchivo(FileUploadEvent event) throws IOException {
        System.out.print(idQueja);
        Queja queja = quejaService.getById(Integer.parseInt(idQueja));
        System.out.print(queja);
        Documentos documento = new Documentos();
        final UploadedFile uploadedFile = event.getFile();
        archivo = uploadedFile;
        byte buffer[] = new byte[1024];
        int len;
        
        InputStream input = archivo.getInputstream();
        FileOutputStream out = new FileOutputStream("/home/cobranza/callcenterArchive/quejas/" + archivo.getFileName());
        while ((len = input.read(buffer, 0, 1024)) > 0) {
            out.write(buffer, 0, len);
        }
        out.close();
        
        documento.setNombre(archivo.getFileName());
        documento.setQueja(queja);
        documentoService.save(documento, UsuarioUtil.getUsuarioAutenticado());
        FacesUtil.addMessage("Archivo cargado correctamente.");
    }
}
