//****************************************************************************//
// @file SenderService.java
//
// @description Controlador de correos de salida del portar callcenter
//
// @author Gerardo Blanco
// @date 09/01/2019 (Ultima modificacion)
//****************************************************************************//
package com.itaca.callcenter.services;

import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.domain.entities.Respuesta;
import com.itaca.callcenter.domain.entities.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;

@Stateless
public class SenderService {

    private static final Logger logger = LogManager.getLogger(SenderService.class);
    @EJB
    LogService logService;
    @EJB
    VariableService variableService;

    public void cargaQueja(Queja queja, Usuario user) throws NamingException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", variableService.getById("mail_auth").getValor());
        props.put("mail.smtp.host", variableService.getById("mail_host").getValor());
        props.put("mail.smtp.port", variableService.getById("mail_port").getValor());
        props.put("mail.transport.protocol", variableService.getById("mail_protocol").getValor());
        props.put("mail.smtp.starttls.enable", variableService.getById("mail_tls").getValor());
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        variableService.getById("mail_user").getValor(),
                        variableService.getById("mail_password").getValor());
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(variableService.getById("mail_user").getValor()));

        //Title
        //Reciever
        if (queja.getCausa().getId() == 5) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(variableService.getById("mail_kobra").getValor()));
        } else {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(variableService.getById("mail_recibe").getValor()));
        }
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(queja.getCorreo().getCorreo()));
        //Title
        msg.setSubject("Queja registrada en Callcenter");
        msg.setSentDate(new Date());
        MimeBodyPart mbp = new MimeBodyPart();
        //Content
        String contenido = "Se registro una queja nueva en el portal de Callcenter\n"
                + "ID: " + queja.getId() + "\n"
                + "Nombre de grupo: " + queja.getGrupo() + "\n"
                + "Nombre de cliente: " + queja.getCliente() + "\n"
                + "Telefono: " + queja.getTelefono() + "\n"
                + "Producto: " + queja.getCausa().getTipo().getTipo() + "\n"
                + "Sucursal: " + queja.getSucursal().getSucursal() + "\n"
                + "Observaciones: " + queja.getObservacion() + "\n\n\n"
                + "NOTA: Favor de emitir la respuesta en la plataforma 01800 Call center. \n" +
"Por disposiciones vigentes emitidas por la CONDUSEF, se obliga a notificar directamente al cliente la respuesta de la queja emitida. ( Presencial )";

        mbp.setText(contenido);
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp);
        msg.setContent(mp);
        Transport.send(msg);
    }

    public void cargaRespuesta(Queja queja, Respuesta respuesta, Usuario user) throws NamingException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", variableService.getById("mail_auth").getValor());
        props.put("mail.smtp.host", variableService.getById("mail_host").getValor());
        props.put("mail.smtp.port", variableService.getById("mail_port").getValor());
        props.put("mail.transport.protocol", variableService.getById("mail_protocol").getValor());
        props.put("mail.smtp.starttls.enable", variableService.getById("mail_tls").getValor());
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        variableService.getById("mail_user").getValor(),
                        variableService.getById("mail_password").getValor());
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(variableService.getById("mail_user").getValor()));
        //Reciever
        if (queja.getCausa().getId() == 5) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(variableService.getById("mail_kobra").getValor()));
        } else {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(variableService.getById("mail_recibe").getValor()));
        }
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(queja.getCorreo().getCorreo()));
        //Title
        msg.setSubject("Respuesta registrada en Callcenter");
        msg.setSentDate(new Date());
        MimeBodyPart mbp = new MimeBodyPart();
        //Content
        String contenido = "Se registro una queja nueva en el portal de Callcenter\n"
                + "ID: " + queja.getId() + "\n"
                + "Nombre de grupo: " + queja.getGrupo() + "\n"
                + "Nombre de cliente: " + queja.getCliente() + "\n"
                + "Telefono: " + queja.getTelefono() + "\n"
                + "Producto: " + queja.getCausa().getTipo().getTipo() + "\n"
                + "Sucursal: " + queja.getSucursal().getSucursal() + "\n"
                + "Respuesta: " + respuesta.getRespuesta() + "\n\n\n"
                + "NOTA: Favor de emitir la respuesta en la plataforma 01800 Call center. \n" +
"Por disposiciones vigentes emitidas por la CONDUSEF, se obliga a notificar directamente al cliente la respuesta de la queja emitida. ( Presencial )";

        mbp.setText(contenido);
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp);
        msg.setContent(mp);
        Transport.send(msg);
    }

    public void cargaReasignacion(Queja queja, Usuario user) throws NamingException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", variableService.getById("mail_auth").getValor());
        props.put("mail.smtp.host", variableService.getById("mail_host").getValor());
        props.put("mail.smtp.port", variableService.getById("mail_port").getValor());
        props.put("mail.transport.protocol", variableService.getById("mail_protocol").getValor());
        props.put("mail.smtp.starttls.enable", variableService.getById("mail_tls").getValor());
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        variableService.getById("mail_user").getValor(),
                        variableService.getById("mail_password").getValor());
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(variableService.getById("mail_user").getValor()));

        //Title
        //Reciever
        if (queja.getCausa().getId() == 5) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(variableService.getById("mail_kobra").getValor()));
        } else {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(variableService.getById("mail_recibe").getValor()));
        }
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(queja.getCorreo().getCorreo()));
        //Title
        msg.setSubject("Queja reasignada en Callcenter");
        msg.setSentDate(new Date());
        MimeBodyPart mbp = new MimeBodyPart();
        //Content
        String contenido = "Se reasigno una queja en el portal de Callcenter\n"
                + "ID: " + queja.getId() + "\n"
                + "Nombre de grupo: " + queja.getGrupo() + "\n"
                + "Nombre de cliente: " + queja.getCliente() + "\n"
                + "Telefono: " + queja.getTelefono() + "\n"
                + "Producto: " + queja.getCausa().getTipo().getTipo() + "\n"
                + "Sucursal: " + queja.getSucursal().getSucursal() + "\n"
                + "Observaciones: " + queja.getObservacion() + "\n\n\n"
                + "NOTA: Favor de emitir la respuesta en la plataforma 01800 Call center. \n" +
"Por disposiciones vigentes emitidas por la CONDUSEF, se obliga a notificar directamente al cliente la respuesta de la queja emitida. ( Presencial )";

        mbp.setText(contenido);
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp);
        msg.setContent(mp);
        Transport.send(msg);
    }

    public void cargaTest() throws NamingException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", variableService.getById("mail_auth").getValor());
        props.put("mail.smtp.host", variableService.getById("mail_host").getValor());
        props.put("mail.smtp.port", variableService.getById("mail_port").getValor());
        props.put("mail.transport.protocol", variableService.getById("mail_protocol").getValor());
        props.put("mail.smtp.starttls.enable", variableService.getById("mail_tls").getValor());
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        variableService.getById("mail_user").getValor(),
                        variableService.getById("mail_password").getValor());
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(variableService.getById("mail_user").getValor()));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(variableService.getById("mail_recibe").getValor()));

        msg.setSubject("Queja registrada en Callcenter");
        msg.setSentDate(new Date());
        MimeBodyPart mbp = new MimeBodyPart();
        mbp.setText("Se registro una queja nueva con el ID:");
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp);
        msg.setContent(mp);
        Transport.send(msg);
    }

}
