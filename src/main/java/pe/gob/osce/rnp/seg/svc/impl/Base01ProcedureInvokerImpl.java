package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class Base01ProcedureInvokerImpl implements Base01ProcedureInvokerRepository {
    private final EntityManager entityManager;

    @Autowired
    public Base01ProcedureInvokerImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public String obtenerOpciones(String ruc) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobteneropciones");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
//        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
//        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);
        storedProcedureQuery.setParameter("ruc", ruc);

        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos los valores de salida
//        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
//        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
//        String outputValue3 = "Paso con exito";
//        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);
            
//        return new Mensaje(outputValue1, outputValue2, outputValue3);
        return "Exito al Buscar";
    }
	
}
