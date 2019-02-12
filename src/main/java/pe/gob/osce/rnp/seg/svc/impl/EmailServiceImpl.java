package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.generic.EmailGeneric;
import pe.gob.osce.rnp.seg.svc.EmailService;
import pe.gob.osce.rnp.seg.utils.Enums;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class EmailServiceImpl extends EmailGeneric implements EmailService {

    public static final Logger LOGGER = LogManager.getLogger(EmailServiceImpl.class);

    private JavaMailSender emailSender;

    private ServletContext context;

    @Value("${spring.profiles.active}")
    private String profile;
    
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, ServletContext context) {
		// TODO Auto-generated constructor stub
    	this.emailSender = emailSender;
    	this.context = context;
	}

    @Override
    public void enviarCorreoActivarUsuario(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator = mimeMessagePreparator(asunto, receptor, contenido);
        try {
            emailSender.send(preparator);
        } catch (MailException ex) {
            LOGGER.info(ex.getMessage());
        }
    }

    @Override
    public void enviarCorreoRecuperarContrasena(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator = mimeMessagePreparator(asunto, receptor, contenido);
        try {
            emailSender.send(preparator);
        } catch (MailException ex) {
            LOGGER.info(ex.getMessage());
        }
    }

    @Override
    public Boolean enviarCorreoInformativo(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator = null;
        try {//YOSELIN.RODRIGUEZ@ITSIGHT.PE|EGMP.RNP.CLAVE@GMAIL.COM
            receptor = profile.equals("development") ? "PETER.CARRANZA@ITSIGHT.PE" : profile.equals("devclient") ? "EGMP.RNP.CLAVE@GMAIL.COM" : receptor;
            preparator = mimeMessagePreparator(asunto, receptor, contenido, new InternetAddress(context.getAttribute("MAIL_USERNAME").toString(),"RNP Plataforma Electrónica"));
            emailSender.send(preparator);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.info(ex.getMessage());
            return false;
        } catch (MailException ex) {
            LOGGER.info(ex.getMessage());
            return false;
        } catch (Exception ex){
            LOGGER.info(ex.getMessage());
            return false;
        }
        return true;
    }
}