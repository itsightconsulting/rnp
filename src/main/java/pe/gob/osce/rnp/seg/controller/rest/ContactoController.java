package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osce.rnp.seg.dao.ContactoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

import java.util.Optional;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

	public ContactoController(){
	}
	
	@Autowired
	private ContactoProcedureInvokerRepository contactoProcedureInvokerRepository; 
	
	@GetMapping("/obtenerCorreoUsuario/{ruc}")
	public ResponseDTO obtenerCorreoUsuario(@PathVariable(value = "ruc") String ruc) {
		Optional<ProcedureOutputDTO> optRes = Optional.ofNullable(contactoProcedureInvokerRepository.obtenerCorreoUsuario(ruc));
		if(optRes.isPresent())
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, optRes.get());
		else
			return new ResponseDTO(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), false, null);
	}

	@GetMapping("/correoRecuperacion/{ruc}")
	public ResponseDTO enviarCorreoRecuperacionPassword(@PathVariable(value = "ruc") String ruc) {
		Optional<ProcedureOutputDTO> optRes = Optional.ofNullable(contactoProcedureInvokerRepository.enviarCorreoRecuperacionPassword(ruc));
		if(optRes.isPresent())
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, null);
		else
			return new ResponseDTO(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), false, null);
	}

	@GetMapping("/validacion/cambio-password/{hashRuc}")
	public ResponseDTO validacionCambioPassword(@PathVariable(value = "hashRuc") String hashRuc) {
		Optional<ProcedureOutputDTO> optRes = Optional.ofNullable(contactoProcedureInvokerRepository.validacionCambioPassword(hashRuc));
		if(optRes.isPresent())
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, null);
		else
			return new ResponseDTO(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), false, null);
	}
}
