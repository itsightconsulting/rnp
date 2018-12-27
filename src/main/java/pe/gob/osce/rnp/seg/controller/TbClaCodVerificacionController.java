package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.PrmClaConfiguracionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.TbClaCodVerificacionProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

@RestController
@RequestMapping("/verificacion")
public class TbClaCodVerificacionController {
	
	@Autowired
	private TbClaCodVerificacionProcedureInvokerRepository tbClaCodVerificacionProcedureInvokerRepository; 
	
	@RequestMapping("/validar/{ruc}/{desCodVerificacion}")
	public Mensaje validar(@PathVariable(value = "ruc") String ruc, @PathVariable(value = "desCodVerificacion") String desCodVerificacion) {
		return tbClaCodVerificacionProcedureInvokerRepository.validarCodVerificacion(ruc, desCodVerificacion);
	}
	
}
