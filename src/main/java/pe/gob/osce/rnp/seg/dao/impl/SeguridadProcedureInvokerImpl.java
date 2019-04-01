package pe.gob.osce.rnp.seg.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osce.rnp.seg.constants.StoredProcedureName;
import pe.gob.osce.rnp.seg.dao.SeguridadProcedureInvokerRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Repository
@Transactional
public class SeguridadProcedureInvokerImpl implements SeguridadProcedureInvokerRepository {

    private static final String P_MENSAJE = "MENSAJE";
    private static final String P_RESPUESTA = "RESPUESTA";
    private static final String P_C_DES_RUC = "C_DES_RUC";
    private static final String P_C_DES_CODVERIFICACION = "C_DES_CODVERIFICACION";
    private static final String P_CLAVE = "CLAVE";

    @Autowired
    private EntityManager em;

    @Override
    public Boolean validarCodVer(String ruc, String codVer) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_VALIDAR_COD_VERIFICACION);
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_C_DES_CODVERIFICACION, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.setParameter(P_C_DES_CODVERIFICACION, codVer);
        spQuery.execute();
        return spQuery.getOutputParameterValue(P_RESPUESTA).equals("1");
    }

    @Override
    public Boolean validarClave(String clave) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_VALIDAR_NUEVA_CLAVE);
        spQuery.registerStoredProcedureParameter(P_CLAVE, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.setParameter(P_CLAVE, clave);
        spQuery.execute();
        return spQuery.getOutputParameterValue(P_RESPUESTA).equals("1");
    }

    @Override
    public Boolean actualizarClave(String ruc, String nuevaClave, String codVer) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_REGISTRAR_NUEVA_CLAVE);
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_CLAVE, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_C_DES_CODVERIFICACION, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.setParameter(P_CLAVE, nuevaClave);
        spQuery.setParameter(P_C_DES_CODVERIFICACION, codVer);
        spQuery.execute();
        return spQuery.getOutputParameterValue(P_RESPUESTA).equals("1");
    }

    @Override
    public Boolean validarIngreso(String ruc, String clave) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_AUTENTICAR_PROVEEDOR);
        spQuery.registerStoredProcedureParameter("RUC", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_CLAVE, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.setParameter("RUC", ruc);
        spQuery.setParameter(P_CLAVE, clave);
        spQuery.execute();
        return spQuery.getOutputParameterValue(P_RESPUESTA).equals("1");
    }
}
