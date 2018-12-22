package pe.gob.osce.rnp.seg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
/**/
@Service
public class ProcedureInvoker implements ProcedureInvokerRepository {
    private final EntityManager entityManager;

    @Autowired
    public ProcedureInvoker(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Mensaje ejecutarSPDemo(){
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sp_insertarPais");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("descripcion", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        storedProcedureQuery.setParameter("descripcion", "value");

        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos los valores de salida
        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
        return new Mensaje();
    }
}
