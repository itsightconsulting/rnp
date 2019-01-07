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
    private final String hostMail = "contoso.peru@gmail.com";

    public MimeMessagePreparator mimeMessagePreparatorHelper(String asunto, String receptor, String copiado, File archivoAdjunto, String contenido) {

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    UTF_8.name());
            helper.setFrom(hostMail);
            helper.setSubject(asunto);
            helper.setTo(new InternetAddress(receptor));
            if(copiado!= null)helper.setCc(copiado);
            if(archivoAdjunto!= null)
                if(archivoAdjunto.length()>0)
                    helper.addAttachment(archivoAdjunto.getName(), archivoAdjunto);
            helper.setText(contenido, true);
        };
        return preparator;
    }

    public MimeMessagePreparator mimeMessagePreparatorHelper(String asunto, String receptor, String[] copiados, File archivoAdjunto, String contenido) {

        MimeMessagePreparator preparator = mimeMessage -> {
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
        return preparator;
    }



    public MimeMessagePreparator mimeMessagePreparatorHelper(String asunto, String receptor, String[] copiados, File[] archivosAdjuntos, String contenido) {

        MimeMessagePreparator preparator = mimeMessage -> {
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
            if(archivosAdjuntos!= null && archivosAdjuntos.length>0)
                for (int i=0;i<archivosAdjuntos.length;i++){
                    if(archivosAdjuntos[i].length()>0)
                        helper.addAttachment(archivosAdjuntos[i].getName(), archivosAdjuntos[i]);
                }
            helper.setText(contenido, true);
        };
        return preparator;
    }

    public MimeMessagePreparator mimeMessagePreparator(String asunto, String receptor, String contenido) {

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setFrom(hostMail);
            mimeMessage.setSubject(asunto);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mimeMessage.setContent(contenido
                    , "text/html; charset=utf-8");
        };
        return preparator;
    }

    public MimeMessagePreparator mimeMessagePreparator(String asunto, String receptor, String contenido, String copiado) {

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setFrom(hostMail);
            mimeMessage.setSubject(asunto);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            if(copiado!=null && copiado.length() > 0) {
                mimeMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(copiado));
            }
            mimeMessage.setContent(contenido
                    , "text/html; charset=utf-8");
        };
        return preparator;
    }

    public MimeMessagePreparator mimeMessagePreparator(String asunto, String receptor, String contenido, String[] copiados) {

        MimeMessagePreparator preparator = mimeMessage -> {
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
                    , "text/html; charset=utf-8");
        };
        return preparator;
    }
}
