package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ContactoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.utils.Validador;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class ContactoProcedureInvokerImpl implements ContactoProcedureInvokerRepository {
    private EntityManager entityManager;

    @Autowired
    public ContactoProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public Mensaje obtenerCorreoUsuario(String ruc) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobtenercorreousuario");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validarRuc(ruc)) {
        	System.out.println("valor " + Validador.validarRuc(ruc));
        	System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);	
	        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
	        
	        // Realizamos la llamada al procedimiento
	        storedProcedureQuery.execute();
        
        // Obtenemos los valores de salida
        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
        String outputValue3 = "Salida";
        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);
		}else {
			System.out.println("valor " + Validador.validarRuc(ruc));
			System.out.println("Fallo en la transacción");
		}    
        return new Mensaje();
    }
}
