package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.gob.osce.rnp.seg.dao.ClaveAccesoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje2;
import pe.gob.osce.rnp.seg.model.jpa.dto.CredencialesDto;

@RestController
@RequestMapping("/api/clave")
public class ClaveAccesoController {
	
	@Autowired
	private ClaveAccesoProcedureInvokerRepository claveAccesoProcedureInvokerRepository; 
	
	@GetMapping("/validarProveedor/{ruc}")
	public Mensaje validarProveedor(@PathVariable(value = "ruc") String ruc) {
		return claveAccesoProcedureInvokerRepository.validaProveedorRnp(ruc);
	}
	
	@PostMapping("/guardarClave")
	public Mensaje guardarClave(@RequestBody CredencialesDto credencialesDto) {
		System.out.println(credencialesDto.getRuc() + "|" + credencialesDto.getClave());
		return claveAccesoProcedureInvokerRepository.guardarClave(credencialesDto.getRuc(), credencialesDto.getClave());
	}
	
	@PostMapping("/validaUsuario")
	public Mensaje validaUsuario(@RequestParam(value = "ruc") String ruc, @RequestParam(value = "clave") String clave) {
		System.out.println(ruc + "|"+clave);
		return claveAccesoProcedureInvokerRepository.validaUsuario(ruc, clave);
	}
	
	@GetMapping("/validaNuevaClave/{clave1}/{clave2}")
	public Mensaje validaNuevaClave(@PathVariable(value = "clave1") String clave1, @PathVariable(value = "clave2") String clave2) {
		return claveAccesoProcedureInvokerRepository.validaNuevaClave(clave1, clave2);
	}
	
}
