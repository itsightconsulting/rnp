package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Opcion;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/base01")
public class Base01Controller {
	
	@Autowired
	private Base01ProcedureInvokerRepository base01ProcedureInvokerRepository; 
	
	@GetMapping("/obtenerOpciones/{ruc}")
	public ResponseDTO obtenerOpciones(@PathVariable(value = "ruc") String ruc) {
		Optional<List<Opcion>> optionalLst = Optional.ofNullable(base01ProcedureInvokerRepository.obtenerOpciones(ruc));
		if(optionalLst.isPresent()){
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, optionalLst.get());
		}
		return new ResponseDTO(Enums.ResponseCode.EMPTY_RESPONSE.get(), false, optionalLst.get());
	}
	
}
