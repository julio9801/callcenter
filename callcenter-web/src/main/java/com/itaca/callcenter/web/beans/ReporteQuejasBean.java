//****************************************************************************//
// @file ReporteQuejassBean.java
//
// @description Controlador para la crezcion del reporte a ser descarga
//
// @dependants
//  └── reporteQuejas.xhtml
//
// @author Gerardo Blanco
// @date 10/01/2019
//****************************************************************************//

package com.itaca.callcenter.web.beans;

import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Respuesta;
import com.itaca.callcenter.services.QuejaService;
import com.itaca.callcenter.services.RespuestaService;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class ReporteQuejasBean implements Serializable {
    private static final Logger logger = LogManager.getLogger(UsuarioBean.class);
    private static final long serialVersionUID = -7820209981001289767L;
    
    //Services
    @EJB
    private QuejaService quejaService;
    @EJB
    private RespuestaService respuestaService;

    public StreamedContent getFile() throws IOException{
        logger.info("Ejecutando getFile() para acciones");

        CharArrayWriter writer = new CharArrayWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL.withQuoteMode(QuoteMode.ALL));
        
        List<Queja> list = quejaService.getCatalog();
        printer.printRecord(
            "Id de queja",
            "Producto",
            "Causa",
            "Area",
            "Fecha de Alta",
            "Fecha de Cierre",
            "Estado",
            "Region",
            "Sucursal",
            "Grupo",
            "Cliente",
            "Tipo de cliente",
            "Correo asignado",
            "Ultima respuesta",
            "Fecha de respuesta",
            "Usuario de respuesta",
            "Dias desde ultima transaccion"
        );
        
        for (int i = 0; i < list.size(); i++) {//Recorre Demandas
            Queja queja = list.get(i);
            
            //Get last respuesta
            String respuestaText = "";
            String fechaRespuesta = "";
            String usuarioRespuesta = "";
            
            //Estimate inactive days
            Date today = new Date();
            
            Integer diasTranscurridos = 0; 
            if(queja.getEstatus().contains("activo")){
                long diff = today.getTime() - queja.getRegistro().getTime();
                diasTranscurridos = Math.round(diff / (1000*60*60*24));
            }
            
            
            List<Respuesta> respuestas = respuestaService.getCatalogByQueja(queja.getId());
            if(!respuestas.isEmpty()){
                Respuesta respuesta = respuestas.get(respuestas.size() - 1);
                respuestaText = respuesta.getRespuesta();
                fechaRespuesta = respuesta.getFecha().toString();
                usuarioRespuesta = respuesta.getUsuario().getFullName();
                
                long diff = today.getTime() - respuesta.getFecha().getTime();
                diasTranscurridos = Math.round(diff / (1000*60*60*24));
            }
                
            if(queja.getEstatus().contains("cerrado")){
                long diff = queja.getCambio().getTime() - queja.getRegistro().getTime();
                diasTranscurridos = Math.round(diff / (1000*60*60*24));
            }
            
            //Turn cierre into string if status == abierto
            String cambio = queja.getCambio().toString();
            if(queja.getEstatus().contains("activo"))
                cambio = "";
            
            //Dicide cliente if anonimo
            String cliente = queja.getCliente();
            if(queja.getReportador().getTipo().contains("ANONIMO"))
                cliente = "ANONIMO";
            System.out.print(queja.getCausa().getTipo().getTipo());
            //Crea archivo
            printer.printRecord(
                    queja.getId(),
                    queja.getCausa().getTipo().getTipo(),
                    queja.getCausa().getCausa(),
                    queja.getCorreo().getArea().getArea(),
                    queja.getRegistro().toString(),
                    cambio,
                    queja.getEstatus(),
                    queja.getSucursal().getIdregion().getNombre(),
                    queja.getSucursal().getSucursal(),
                    queja.getGrupo(),
                    cliente,
                    queja.getReportador().getTipo(),
                    queja.getCorreo().getCorreo(),
                    respuestaText,
                    fechaRespuesta,
                    usuarioRespuesta,
                    diasTranscurridos
            );
        }
        
        printer.close();
        StreamedContent file = new ByteArrayContent(writer.toString().getBytes(),"text/csv","reporteQuejas.csv") ;
        return file;
    }
}
