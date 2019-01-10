package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ClaveAccesoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;
import pe.gob.osce.rnp.seg.utils.Validador;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class ClaveAccesoProcedureInvokerImpl implements ClaveAccesoProcedureInvokerRepository {

    private EntityManager entityManager;

    @Autowired
    public ClaveAccesoProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProcedureOutputDTO validaProveedorRnp(String ruc) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidaproveedorrnp");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validRuc(ruc)) {
            System.out.println("valor " + Validador.validRuc(ruc));
            storedProcedureQuery.setParameter("ruc", ruc);

            // Realizamos la llamada al procedimiento
            storedProcedureQuery.execute();

            // Obtenemos los valores de salida
            String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
            String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
            String outputValue3 = "Paso con exito";
            System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);
        }else {
            System.out.println("valor " + Validador.validRuc(ruc));
            System.out.println("Fallo en la transacción");
        }
        return new ProcedureOutputDTO();
//        return "Exito al Buscar";
    }

    @Override
    public ProcedureOutputDTO guardarClave(String ruc, String clave) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spguardarclave");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("CLAVE", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validRuc(ruc) && Validador.validarClave(clave)) {
            System.out.println("valor " + Validador.validRuc(ruc));
            System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);
            storedProcedureQuery.setParameter("C_DES_RUC", ruc);
            System.out.println("valor Clave " + Validador.validarClave(clave));
            storedProcedureQuery.setParameter("CLAVE", clave);
            // Realizamos la llamada al procedimiento
            storedProcedureQuery.execute();

            // Obtenemos los valores de salida
            String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
            String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
            String outputValue3 = "Registro con exito";
            System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);
        }else {
            System.out.println("valor " + Validador.validRuc(ruc));
            System.out.println("valor Clave " + Validador.validarClave(clave));
            System.out.println("Fallo en la transacción");
        }
        return new ProcedureOutputDTO();
    }

    @Override
    public ProcedureOutputDTO validaUsuario(String ruc, String clave) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidausuario");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("CLAVE", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validRuc(ruc)) {
            System.out.println("valor " + Validador.validRuc(ruc));
            System.out.println("TOMA PARAMETRO DE ENTRADA ___" + ruc);
            storedProcedureQuery.setParameter("RUC", ruc);
            storedProcedureQuery.setParameter("CLAVE", clave);

            // Realizamos la llamada al procedimiento
            storedProcedureQuery.execute();

            // Obtenemos los valores de salida
            String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
            String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
            String outputValue3 = "validado con exito";
            System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);
        }else {
            System.out.println("valor " + Validador.validRuc(ruc));
            System.out.println("Fallo en la transacción");
        }
        return new ProcedureOutputDTO();
    }

    @Override
    public ProcedureOutputDTO validaNuevaClave(String clave1, String clave2) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidanuevaclave");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("CLAVE1", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("CLAVE2", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        storedProcedureQuery.setParameter("CLAVE1", clave1);
        storedProcedureQuery.setParameter("CLAVE2", clave2);

        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos los valores de salida
        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
        String outputValue3 = "validado nueva clave con exito";
        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2 + " | OUT3: "+outputValue3);

        return new ProcedureOutputDTO(outputValue1, outputValue2);
    }
}