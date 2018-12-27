package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.ClaveAccesoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.ContactoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje2;

@RestController
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired
	private ContactoProcedureInvokerRepository contactoProcedureInvokerRepository; 
	
	@RequestMapping("/obtenerCorreoUsuario/{ruc}")
	public Mensaje obtenerCorreoUsuario(@PathVariable(value = "ruc") String ruc) {
		return contactoProcedureInvokerRepository.obtenerCorreoUsuario(ruc);
	}
	
	
}
