package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

@RestController
@RequestMapping("/configuracion")
public class PrmClaConfigurarionController {
	
	@Autowired
	private PrmClaConfiguracionProcedureInvokerRepository prmClaConfiguracionProcedureInvokerRepository; 
	
	@PostMapping("/registrar")
	public Mensaje obtenerOpciones(@RequestParam(value = "ruc") String ruc, @RequestParam(value = "correo") String correo,
								   @RequestParam(value = "codUid") String codUid, @RequestParam(value = "desIp") String desIp) {
		return prmClaConfiguracionProcedureInvokerRepository.registrarCodVerificacion(ruc, correo, codUid, desIp);
	}
	
}
