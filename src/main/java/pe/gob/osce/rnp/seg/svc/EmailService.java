package pe.gob.osce.rnp.seg.svc;

public interface EmailService {

	Boolean enviarCorreoInformativo(String asunto, String receptor, String contenido);
}
