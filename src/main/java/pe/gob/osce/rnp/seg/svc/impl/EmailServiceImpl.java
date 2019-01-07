package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.generic.EmailGeneric;
import pe.gob.osce.rnp.seg.svc.EmailService;

@Service
public class EmailServiceImpl extends EmailGeneric implements EmailService {


    private JavaMailSender emailSender;
    
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
		// TODO Auto-generated constructor stub
    	this.emailSender = emailSender;
	}

    @Override
    public void enviarCorreoActivarUsuario(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator = mimeMessagePreparator(asunto, receptor, contenido);
        try {
            emailSender.send(preparator);
        } catch (MailException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void enviarCorreoRecuperarContrasena(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator = mimeMessagePreparator(asunto, receptor, contenido);
        try {
            emailSender.send(preparator);
        } catch (MailException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void enviarCorreoInformativo(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator = mimeMessagePreparator(asunto, receptor, contenido);
        try {
            emailSender.send(preparator);
        } catch (MailException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }
}