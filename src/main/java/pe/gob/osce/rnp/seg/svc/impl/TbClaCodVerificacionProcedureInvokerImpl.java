package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.TbClaCodVerificacionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class TbClaCodVerificacionProcedureInvokerImpl implements TbClaCodVerificacionProcedureInvokerRepository{

    private EntityManager entityManager;

    @Autowired
    public TbClaCodVerificacionProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public ProcedureOutputDTO validarCodVerificacion(String ruc, String desCodVerificacion) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidarcodverificacion");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_CODVERIFICACION", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
        storedProcedureQuery.setParameter("C_DES_CODVERIFICACION", desCodVerificacion);

        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos los valores de salida
        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
        String outputValue3 = "Paso con exito";
        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
            
        return new ProcedureOutputDTO(outputValue1, outputValue2);
    }
	
}
