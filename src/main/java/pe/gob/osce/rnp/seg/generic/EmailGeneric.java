package pe.gob.osce.rnp.seg.generic;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import pe.gob.osce.rnp.seg.utils.Utilitarios;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.io.File;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class EmailGeneric {

    //@Value("${spring.mail.username}")
    private static String hostMail = "";
    
    private static final String MAIL_CONTENT_TYPE = "text/html; charset=utf-8";

    public MimeMessagePreparator mimeMessagePreparatorHelper(String asunto, String receptor, String[] copiados, File archivoAdjunto, String contenido) {

        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    UTF_8.name());
            helper.setFrom(hostMail);
            helper.setSubject(asunto);
            helper.setTo(new InternetAddress(receptor));
            if(copiados!=null && copiados.length>0) {
                String[] copiadosFilter = Utilitarios.filterStringArray(copiados);
                if(copiadosFilter.length>0) {
                    helper.setCc(copiados);
                }
            }
            if(archivoAdjunto != null && archivoAdjunto.length()>0)helper.addAttachment(archivoAdjunto.getName(), archivoAdjunto);
            helper.setText(contenido, true);
        };
    }

    public MimeMessagePreparator mimeMessagePreparator(String asunto, String receptor, String contenido) {

        return mimeMessage -> {
            mimeMessage.setFrom(hostMail);
            mimeMessage.setSubject(asunto);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mimeMessage.setContent(contenido
                    , MAIL_CONTENT_TYPE);
        };
    }

    public MimeMessagePreparator mimeMessagePreparator(String asunto, String receptor, String contenido, InternetAddress fromAddress) {

        return mimeMessage -> {
            mimeMessage.setFrom(fromAddress);
            mimeMessage.setSubject(asunto);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mimeMessage.setContent(contenido
                    , MAIL_CONTENT_TYPE);
        };
    }

    public MimeMessagePreparator mimeMessagePreparator(String asunto, String receptor, String contenido, String copiado) {

        return mimeMessage -> {
            mimeMessage.setFrom(hostMail);
            mimeMessage.setSubject(asunto);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            if(copiado!=null && copiado.length() > 0) {
                mimeMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(copiado));
            }
            mimeMessage.setContent(contenido
                    , MAIL_CONTENT_TYPE);
        };
    }

    public MimeMessagePreparator mimeMessagePreparator(String asunto, String receptor, String contenido, String[] copiados) {

        return mimeMessage -> {
            mimeMessage.setFrom(hostMail);
            mimeMessage.setSubject(asunto);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            if(copiados!=null && copiados.length>0) {
                String[] copiadosFilter = Utilitarios.filterStringArray(copiados);
                if(copiadosFilter.length>0) {
                    InternetAddress[] direcciones = new InternetAddress[copiadosFilter.length];
                    for (int i = 0; i < copiadosFilter.length;i++){
                        direcciones[i] = new InternetAddress(copiadosFilter[i]);
                    }
                    mimeMessage.setRecipients(Message.RecipientType.CC, direcciones);
                }
            }
            mimeMessage.setContent(contenido
                    , MAIL_CONTENT_TYPE);
        };
    }
}
