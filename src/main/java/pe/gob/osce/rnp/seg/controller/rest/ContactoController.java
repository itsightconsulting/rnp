package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osce.rnp.seg.dao.ContactoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {
	
	@Autowired
	private ContactoProcedureInvokerRepository contactoProcedureInvokerRepository; 
	
	@GetMapping("/obtenerCorreoUsuario/{ruc}")
	public ResponseDTO obtenerCorreoUsuario(@PathVariable(value = "ruc") String ruc) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, contactoProcedureInvokerRepository.obtenerCorreoUsuario(ruc));
	}

}
