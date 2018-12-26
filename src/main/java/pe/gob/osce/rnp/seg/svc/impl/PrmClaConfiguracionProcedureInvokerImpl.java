package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class PrmClaConfiguracionProcedureInvokerImpl implements PrmClaConfiguracionProcedureInvokerRepository {
    private final EntityManager entityManager;

    @Autowired
    public PrmClaConfiguracionProcedureInvokerImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public Mensaje registrarCodVerificacion(String ruc, String correo, String codUid, String desIp) {
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
        System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);
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
            
        return new Mensaje(outputValue1, outputValue2, outputValue3);
//        return "Exito al Buscar";
    }
	
}
