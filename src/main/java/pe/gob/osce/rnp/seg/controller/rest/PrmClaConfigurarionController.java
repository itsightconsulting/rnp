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
	
<<<<<<< HEAD
	@PostMapping("/registrar-codigo-verificacion")
	public ResponseDTO registrarCodigoVerificacion(@ModelAttribute CodigoVerificacionDTO codigoVerificacionDto) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, prmClaConfiguracionProcedureInvokerRepository.registrarCodVerificacion(codigoVerificacionDto));
=======
	@PostMapping("/registrarCodigoVerificacion")
	public ResponseDTO registrarCodigoVerificacion(@RequestParam(value = "ruc") String ruc, @RequestParam(value = "correo") String correo,
								   @RequestParam(value = "codUid") String codUid, @RequestParam(value = "desIp") String desIp) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, prmClaConfiguracionProcedureInvokerRepository.registrarCodVerificacion(ruc, correo, codUid, desIp));
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
	}
}
