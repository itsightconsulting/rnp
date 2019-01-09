package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.CodigoVerificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;

public interface PrmClaConfiguracionProcedureInvokerRepository {

	ProcedureOutputDTO registrarCodVerificacion(CodigoVerificacionDTO codigoVerificacionDto);
}
