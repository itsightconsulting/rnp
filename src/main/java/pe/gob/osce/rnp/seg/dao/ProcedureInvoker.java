package pe.gob.osce.rnp.seg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class ProcedureInvoker implements ProcedureInvokerRepository {
    private final EntityManager entityManager;

    @Autowired
    public ProcedureInvoker(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//	@Override
//	public Mensaje obtenerOpciones(String ruc) {
//	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobteneropciones");
//	    
//        // Registrar los parámetros de entrada y salida
//        storedProcedureQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
//        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
//        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);
//
//        // Configuramos el valor de entrada
//        System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);
//        storedProcedureQuery.setParameter("ruc", ruc);
//
//        // Realizamos la llamada al procedimiento
//        storedProcedureQuery.execute();
//
//        // Obtenemos los valores de salida
//        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
//        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
//        String outputValue3 = "Paso con exito";
//        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);
//            
//        return new Mensaje(outputValue1, outputValue2, outputValue3);
//    }
//	
//	@Override
//	public Mensaje registrarCodVerificacion(String ruc, String correo, String codUid, String desIp) {
//	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spregistrarcodverificacion");
//	    
//        // Registrar los parámetros de entrada y salida
//        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
//        storedProcedureQuery.registerStoredProcedureParameter("C_DES_CORREO", String.class, ParameterMode.IN);
//        storedProcedureQuery.registerStoredProcedureParameter("C_COD_UID", String.class, ParameterMode.IN);
//        storedProcedureQuery.registerStoredProcedureParameter("C_DES_IP", String.class, ParameterMode.IN);
//        storedProcedureQuery.registerStoredProcedureParameter("C_DES_CODVERIFICACION", String.class, ParameterMode.OUT);
//        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
//        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);
//
//        // Configuramos el valor de entrada
//        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
//        storedProcedureQuery.setParameter("C_DES_CORREO", ruc);
//        storedProcedureQuery.setParameter("C_COD_UID", ruc);
//        storedProcedureQuery.setParameter("C_DES_IP", ruc);
//        
//
//        // Realizamos la llamada al procedimiento
//        storedProcedureQuery.execute();
//
//        // Obtenemos los valores de salida
//        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
//        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
//        String outputValue3 = (String) storedProcedureQuery.getOutputParameterValue("C_DES_CODVERIFICACION");
////            System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
//        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + "| OUT3: "+outputValue3);
//        return new Mensaje(outputValue1, outputValue2, outputValue3);
//    }

//    @Override
//    public Mensaje ejecutarSPDemo(String nombre){
//        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sp_insertarPais");
//
//        // Registrar los parámetros de entrada y salida
//        storedProcedureQuery.registerStoredProcedureParameter("descripcion", String.class, ParameterMode.IN);
//        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
//        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);
//
//        // Configuramos el valor de entrada
//        storedProcedureQuery.setParameter("descripcion", nombre);
//
//        // Realizamos la llamada al procedimiento
//        storedProcedureQuery.execute();
//
//        // Obtenemos los valores de salida
//        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
//        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
//        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
//        return new Mensaje(outputValue1, outputValue2);
//    }
}
