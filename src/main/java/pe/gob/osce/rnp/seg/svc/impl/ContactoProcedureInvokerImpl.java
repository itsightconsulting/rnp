package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ContactoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.svc.EmailService;
import pe.gob.osce.rnp.seg.utils.Enums;
import pe.gob.osce.rnp.seg.utils.MailContents;
import pe.gob.osce.rnp.seg.utils.Parseador;
import pe.gob.osce.rnp.seg.utils.Validador;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Optional;

@Service
public class ContactoProcedureInvokerImpl implements ContactoProcedureInvokerRepository {

    private static final String hashSchema = "RoNsPce";

    private EntityManager entityManager;

    private EmailService emailService;

    @Autowired
    public ContactoProcedureInvokerImpl(EntityManager entityManager, EmailService emailService) {
        this.entityManager = entityManager;
        this.emailService = emailService;
    }

	@Override
	public ProcedureOutputDTO obtenerCorreoUsuario(String ruc) {
	    if(Validador.validRuc(ruc)){
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobtenercorreousuario");
            // Registrar los parámetros de entrada y salida
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

            // Configuramos el valor de entrada
            System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);
            storedProcedureQuery.setParameter("C_DES_RUC", ruc);

            // Realizamos la llamada al procedimiento
            storedProcedureQuery.execute();

            // Obtenemos los valores de salida
            String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
            String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
            String outputValue3 = "Paso con exito";
            System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
            return new ProcedureOutputDTO(outputValue1, outputValue2);
        }
        return null;
    }

    @Override
    public ProcedureOutputDTO enviarCorreoRecuperacionPassword(String ruc) {
        if(Validador.validRuc(ruc)) {
            Optional<ProcedureOutputDTO> optRes = Optional.ofNullable(obtenerCorreoUsuario(ruc));
            if(optRes.isPresent()) {
                String codVerificacion = "O2X1Z1";
                StringBuilder sbContentMail = MailContents.recuperarPassword(ruc, codVerificacion);
                emailService.enviarCorreoRecuperarContrasena("Olvidó de contraseña", optRes.get().getMensaje().trim(), sbContentMail.toString());
                return new ProcedureOutputDTO();
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public ProcedureOutputDTO validacionCambioPassword(String hashRuc) {
        String ruc = String.valueOf(Parseador.getDecodeHash32Id(hashSchema, hashRuc));
        if(Validador.validRuc(ruc)) {
            Optional<ProcedureOutputDTO> optRes = Optional.ofNullable(obtenerCorreoUsuario(ruc));
            if(optRes.isPresent()) {
                String codVerificacion = "O2X1Z1";
                StringBuilder sbContentMail = MailContents.recuperarPassword(ruc, codVerificacion);
                emailService.enviarCorreoRecuperarContrasena("Olvidó de contraseña", optRes.get().getMensaje().trim(), sbContentMail.toString());
                return new ProcedureOutputDTO();
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public Respuesta<String> obtenerCorreo(String ruc) {
        if(Validador.validRuc(ruc)){
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("spobtenercorreousuario");
            // Registrar los parámetros de entrada y salida
            spQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

            // Configuramos el valor de entrada
            spQuery.setParameter("C_DES_RUC", ruc);
            // Realizamos la llamada al procedimiento
            spQuery.execute();
            // Obtenemos los valores de salida
            boolean existeRuc = spQuery.getOutputParameterValue("respuesta") == "1";

            if(existeRuc){
                String correo = spQuery.getOutputParameterValue("mensaje").toString();
                return new Respuesta<>(Enums.ResponseCode.EXITO_GENERICA.get(), 1, correo);
            }
            return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        }
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }
}
