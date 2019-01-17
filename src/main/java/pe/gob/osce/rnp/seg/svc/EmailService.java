package pe.gob.osce.rnp.seg.svc;

public interface EmailService {

	void enviarCorreoActivarUsuario(String asunto, String receptor, String contenido);

	void enviarCorreoRecuperarContrasena(String asunto, String receptor, String contenido);

	Boolean enviarCorreoInformativo(String asunto, String receptor, String contenido);
}
