package pe.gob.osce.rnp.seg.svc.impl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.CodigoVerificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;
import pe.gob.osce.rnp.seg.utils.Validador;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
=======

import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje2;
import pe.gob.osce.rnp.seg.utils.Validador;
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc

@Service
public class PrmClaConfiguracionProcedureInvokerImpl implements PrmClaConfiguracionProcedureInvokerRepository {
    private EntityManager entityManager;

    @Autowired
    public PrmClaConfiguracionProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
<<<<<<< HEAD
	public ProcedureOutputDTO registrarCodVerificacion(CodigoVerificacionDTO codigoVerificacionDto) {
        if(Validador.validarUsuario(Long.valueOf(String.valueOf(codigoVerificacionDto.getRuc()))) && Validador.validarCorreo(codigoVerificacionDto.getCorreo())) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spregistrarcodverificacion");

            // Registrar los parámetros de entrada y salida
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_CORREO", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_COD_UID", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_IP", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_CODVERIFICACION", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

            // Realizamos la llamada al procedimiento
            storedProcedureQuery.execute();

            // Obtenemos los valores de salida
            String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
            String outputValue3 = (String) storedProcedureQuery.getOutputParameterValue("C_DES_CODVERIFICACION");

            return new ProcedureOutputDTO(outputValue2, outputValue3);
        }
        return null;
=======
	public Mensaje2 registrarCodVerificacion(String ruc, String correo, String codUid, String desIp) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spregistrarcodverificacion");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_CORREO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_COD_UID", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_IP", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_CODVERIFICACION", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validRuc(ruc) && Validador.validarCorreo(correo) && codUid != "" && desIp != "") {
	     	System.out.println("valor " + Validador.validRuc(ruc));
	     	System.out.println("valor correo " + Validador.validarCorreo(correo));
	        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
	        storedProcedureQuery.setParameter("C_DES_CORREO", correo);
	        storedProcedureQuery.setParameter("C_COD_UID", codUid);
	        storedProcedureQuery.setParameter("C_DES_IP", desIp);
	        
	
	        // Realizamos la llamada al procedimiento
	        storedProcedureQuery.execute();
	
	        // Obtenemos los valores de salida
	        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
	        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
	        String outputValue3 = (String) storedProcedureQuery.getOutputParameterValue("C_DES_CODVERIFICACION");
	        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);
	        
	        return new Mensaje2(outputValue1,outputValue2, outputValue3);
        }else {
			System.out.println("valor " + Validador.validRuc(ruc));
			System.out.println("valor correo " + Validador.validarCorreo(correo));
			System.out.println("Fallo en la transacción");
		}        
        return new Mensaje2();
//        return "Exito al Buscar";
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
    }
}
