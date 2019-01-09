package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.CodigoVerificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping("/api/configuracion")
public class PrmClaConfigurarionController {
	
	@Autowired
	private PrmClaConfiguracionProcedureInvokerRepository prmClaConfiguracionProcedureInvokerRepository; 
	
	@PostMapping("/registrar-codigo-verificacion")
	public ResponseDTO registrarCodigoVerificacion(@ModelAttribute CodigoVerificacionDTO codigoVerificacionDto) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, prmClaConfiguracionProcedureInvokerRepository.registrarCodVerificacion(codigoVerificacionDto));
	}
}
