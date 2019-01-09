package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osce.rnp.seg.dao.TbClaCodVerificacionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping(value = "/api/verificacion")
public class TbClaCodVerificacionController {
	
	@Autowired
	private TbClaCodVerificacionProcedureInvokerRepository tbClaCodVerificacionProcedureInvokerRepository; 
	
	@GetMapping("/validar/{ruc}/{desCodVerificacion}")
	public ResponseDTO validacionCodigoCambioPassword(@PathVariable(value = "ruc") String ruc, @PathVariable(value = "desCodVerificacion") String desCodVerificacion) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, tbClaCodVerificacionProcedureInvokerRepository.validarCodVerificacion(ruc, desCodVerificacion));
	}
	
}
