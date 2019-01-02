package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.dao.ClaveAccesoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping("/api/clave")
public class ClaveAccesoController {
	
	@Autowired
	private ClaveAccesoProcedureInvokerRepository claveAccesoProcedureInvokerRepository; 
	
	@GetMapping("/validarProveedor/{ruc}")
	public ResponseDTO validarProveedor(@PathVariable(value = "ruc") String ruc) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, claveAccesoProcedureInvokerRepository.validaProveedorRnp(ruc));
	}
	
	@PostMapping("/guardarClave")
	public ResponseDTO guardarClave(@RequestParam(value = "clave") String clave, @RequestParam(value = "reClave") String reClave) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, claveAccesoProcedureInvokerRepository.guardarClave(clave, reClave));
	}
	
	@PostMapping("/validaUsuario")
	public ResponseDTO validaUsuario(@RequestParam(value = "ruc") String ruc, @RequestParam(value = "clave") String clave) {
		System.out.println(ruc + "|"+ clave);
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, "Login exitoso");
		//return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, claveAccesoProcedureInvokerRepository.validaUsuario(ruc, clave));
	}
	
	@GetMapping("/validaNuevaClave/{clave1}/{clave2}")
	public ResponseDTO validaNuevaClave(@PathVariable(value = "clave1") String clave1, @PathVariable(value = "clave2") String clave2) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, claveAccesoProcedureInvokerRepository.validaNuevaClave(clave1, clave2));
	}
	
}
