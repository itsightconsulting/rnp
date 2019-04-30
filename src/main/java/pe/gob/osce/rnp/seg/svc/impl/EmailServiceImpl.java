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

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl extends EmailGeneric implements EmailService {

    public static final Logger LOGGER = LogManager.getLogger(EmailServiceImpl.class);

    private JavaMailSender emailSender;

    private ServletContext context;

    @Value("${spring.profiles.active}")
    private String profile;
    
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, ServletContext context) {
    	this.emailSender = emailSender;
    	this.context = context;
	}

    @Override
    public Boolean enviarCorreoInformativo(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator;
        try {
            receptor = profile.equals("production") ? receptor : "EGMP.RNP.CLAVE@GMAIL.COM";
            preparator = mimeMessagePreparator(asunto, receptor, contenido, new InternetAddress(context.getAttribute("MAIL_USERNAME").toString(),"RNP Plataforma Electrónica"));
            emailSender.send(preparator);
        } catch (UnsupportedEncodingException | MailException | NullPointerException ex) {
            LOGGER.error(ex.getMessage());
            return false;
        }
        return true;
    }
}
