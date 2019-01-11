package pe.gob.osce.rnp.seg.svc.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.dao.ContactoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.dto.CorreosDTO;
import pe.gob.osce.rnp.seg.utils.Validador;

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
        if(Validador.validRuc(ruc)) {
        	System.out.println("valor " + Validador.validRuc(ruc));
        	System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);	
	        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
	        
	        // Realizamos la llamada al procedimiento
	        storedProcedureQuery.execute();
        
	        // Obtenemos los valores de salida
	        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
	        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
	        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
	        return new Mensaje(outputValue1,outputValue2);
		}else {
			System.out.println("valor " + Validador.validRuc(ruc));
			System.out.println("Fallo en la transacción");
		}    
        return new Mensaje();
    }
	
	@Override
	public List<CorreosDTO> obtenerCorreoRepresentante(String ruc) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobtenercorreorepresentante");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
        List<Object[]> resultSet = storedProcedureQuery.getResultList();
        int size = resultSet.size();
	        if(size > 0){
	            List<CorreosDTO> opciones = new ArrayList<>(resultSet.size());
	            resultSet.forEach(x-> opciones.add(new CorreosDTO(String.valueOf(x[0]), String.valueOf(x[1]))));
	            return opciones;
	        }else{
	            System.out.println(">>EMPTY RESULT SET");
	        }
    
        return null;
    }
}
