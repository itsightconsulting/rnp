package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.ClaveAccesoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje2;

@RestController
@RequestMapping("/clave")
public class ClaveAccesoController {
	
	@Autowired
	private ClaveAccesoProcedureInvokerRepository claveAccesoProcedureInvokerRepository; 
	
	@RequestMapping("/validarProveedor/{ruc}")
	public Mensaje validarProveedor(@PathVariable(value = "ruc") String ruc) {
		return claveAccesoProcedureInvokerRepository.validaProveedorRnp(ruc);
	}
	
	@RequestMapping("/guardarClave/{ruc}/{clave}")
	public Mensaje guardarClave(@PathVariable(value = "ruc") String ruc, @PathVariable(value = "clave") String clave) {
		return claveAccesoProcedureInvokerRepository.guardarClave(ruc, clave);
	}
	
	@RequestMapping("/validaUsuario/{ruc}/{clave}")
	public Mensaje validaUsuario(@PathVariable(value = "ruc") String ruc, @PathVariable(value = "clave") String clave) {
		return claveAccesoProcedureInvokerRepository.validaUsuario(ruc, clave);
	}
	
	@RequestMapping("/validaNuevaClave/{clave1}/{clave2}")
	public Mensaje validaNuevaClave(@PathVariable(value = "clave1") String clave1, @PathVariable(value = "clave2") String clave2) {
		return claveAccesoProcedureInvokerRepository.validaNuevaClave(clave1, clave2);
	}
	
}
