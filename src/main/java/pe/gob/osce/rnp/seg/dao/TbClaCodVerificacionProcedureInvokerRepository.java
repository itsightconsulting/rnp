package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;

public interface TbClaCodVerificacionProcedureInvokerRepository {

    ProcedureOutputDTO validarCodVerificacion(String ruc, String desCodVerificacion);
}
