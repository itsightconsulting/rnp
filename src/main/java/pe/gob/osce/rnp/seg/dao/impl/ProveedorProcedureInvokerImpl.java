package pe.gob.osce.rnp.seg.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.osce.rnp.seg.constants.StoredProcedureName;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvoker;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ProveedorProcedureInvokerImpl implements ProveedorProcedureInvoker {

    @Autowired
    private EntityManager em;

    @Override
    public Boolean validarExistencia(String ruc) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_EXISTE_PROVEEDOR);
        spQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);
        spQuery.setParameter("ruc", ruc);
        spQuery.execute();
        return spQuery.getOutputParameterValue("respuesta") == "1";
    }

    @Override
    public OpcionDTO obtenerOpcionesCambioPassword(String ruc) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_OPCS_P_CAMBIO_PASS);
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
        spQuery.setParameter("ruc", ruc);
        List<Object[]> resultSet = spQuery.getResultList();

        int size = resultSet.size();
        if(size > 0){
            OpcionDTO opcs = new OpcionDTO();
            resultSet.forEach(x-> {
                opcs.setOpcion1(String.valueOf(x[0]));
                opcs.setOpcion2(String.valueOf(x[1]));
                opcs.setOpcion3(String.valueOf(x[2]));
                opcs.setOpcion4(String.valueOf(x[3]));
            });
            return opcs;
        }
        return null;
    }

    @Override
    public String obtenerCorreo(String correo) {
        return null;
    }
}
