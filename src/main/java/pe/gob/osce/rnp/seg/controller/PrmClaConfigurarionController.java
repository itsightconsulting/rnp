package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

@RestController
@RequestMapping("/configuracion")
public class PrmClaConfigurarionController {
	
	@Autowired
	private PrmClaConfiguracionProcedureInvokerRepository prmClaConfiguracionProcedureInvokerRepository; 
	
	@RequestMapping("/registrar/{ruc}/{correo}/{codUid}/{desIp}")
	public Mensaje obtenerOpciones(@PathVariable(value = "ruc") String ruc, @PathVariable(value = "correo") String correo,
			@PathVariable(value = "codUid") String codUid, @PathVariable(value = "desIp") String desIp) {
		return prmClaConfiguracionProcedureInvokerRepository.registrarCodVerificacion(ruc, correo, codUid, desIp);
	}
	
}
