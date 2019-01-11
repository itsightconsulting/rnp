package pe.gob.osce.rnp.seg.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osce.rnp.seg.constants.StoredProcedureName;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvoker;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
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
    public String obtenerCorreo(String ruc) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_OPCS_P_CAMBIO_PASS);
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);
        spQuery.setParameter("ruc", ruc);
        spQuery.execute();
        boolean existeCorreo = spQuery.getOutputParameterValue("respuesta") == "1";
        return existeCorreo ? spQuery.getOutputParameterValue("mensaje").toString() : null;
    }

    @Override
    public String obtenerCodigoVerificacion(String ruc, String correo, String ipCliente) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_REGISTRAR_COD_VERIFICACION);

        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_DES_CORREO", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_DES_IP", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        spQuery.setParameter("C_DES_RUC", ruc);
        spQuery.setParameter("C_DES_CORREO", correo);
        spQuery.setParameter("C_DES_IP", ipCliente);

        spQuery.execute();
        boolean existeRuc = spQuery.getOutputParameterValue("respuesta") == "1";
        return existeRuc ? spQuery.getOutputParameterValue("mensaje").toString() : null;
    }

    @Override
    public ContenidoCorreoPOJO obtenerContenidoCorreoByTipo(int tipo, String ruc, String codVerificacion) {
        String query = StoredProcedureName.FN_OBTENER_BODY_CORREO;
        Query nativeQuery = em.createNativeQuery(query);
        nativeQuery.setParameter(1, tipo);//Integer
        nativeQuery.setParameter(2, ruc);//VARCHAR
        nativeQuery.setParameter(3, new Date());//DATETIME
        nativeQuery.setParameter(4, codVerificacion);//VARCHAR(6)
        nativeQuery.setParameter(5, null);//OBS 1 VARCHAR NULLABLE
        nativeQuery.setParameter(6, null);//OBS 2 VARCHAR NULLABLE
        nativeQuery.setParameter(7, null);//OBS 3 VARCHAR NULLABLE
        nativeQuery.setParameter(8, null);//OBS 4 VARCHAR NULLABLE
        List<Object[]> result =nativeQuery.getResultList();
        return result.size() > 0 ?
                new ContenidoCorreoPOJO(
                    Integer.parseInt(result.get(0)[0].toString()),
                    result.get(0)[1].toString(),
                    result.get(0)[2].toString())
               : null;
    }

    @Override
    public String registrarCorreoEnviado(String ruc, Integer idAsunto, String cuerpoCorreo) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_REGISTRAR_CORREO_ENVIADO);

        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("N_ID_ASUNTO", Integer.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_DES_MENSAJE", String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        spQuery.setParameter("C_DES_RUC", ruc);
        spQuery.setParameter("N_ID_ASUNTO", idAsunto);
        spQuery.setParameter("C_DES_MENSAJE", cuerpoCorreo);
        spQuery.execute();
        return spQuery.getOutputParameterValue("respuesta").toString();
    }
}
