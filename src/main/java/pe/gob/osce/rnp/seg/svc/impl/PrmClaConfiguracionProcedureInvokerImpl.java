package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.CodigoVerificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;
import pe.gob.osce.rnp.seg.utils.Validador;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class PrmClaConfiguracionProcedureInvokerImpl implements PrmClaConfiguracionProcedureInvokerRepository {
    private EntityManager entityManager;

    @Autowired
    public PrmClaConfiguracionProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public ProcedureOutputDTO registrarCodVerificacion(CodigoVerificacionDTO codigoVerificacionDto) {
        if(Validador.validRuc(String.valueOf(codigoVerificacionDto.getRuc())) && Validador.validarCorreo(codigoVerificacionDto.getCorreo())) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spregistrarcodverificacion");

            // Registrar los parámetros de entrada y salida
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_CORREO", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_COD_UID", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_IP", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("C_DES_CODVERIFICACION", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

            // Realizamos la llamada al procedimiento
            storedProcedureQuery.execute();

            // Obtenemos los valores de salida
            String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
            String outputValue3 = (String) storedProcedureQuery.getOutputParameterValue("C_DES_CODVERIFICACION");

            return new ProcedureOutputDTO(outputValue2, outputValue3);
        }
        return null;
    }
}
