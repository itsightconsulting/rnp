package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping("/api/configuracion")
public class PrmClaConfigurarionController {
	
	@Autowired
	private PrmClaConfiguracionProcedureInvokerRepository prmClaConfiguracionProcedureInvokerRepository; 
	
	@PostMapping("/registrarCodigoVerificacion")
	public ResponseDTO registrarCodigoVerificacion(@RequestParam(value = "ruc") String ruc, @RequestParam(value = "correo") String correo,
								   @RequestParam(value = "codUid") String codUid, @RequestParam(value = "desIp") String desIp) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, prmClaConfiguracionProcedureInvokerRepository.registrarCodVerificacion(ruc, correo, codUid, desIp));
	}
	
}
