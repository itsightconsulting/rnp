package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/base01")
public class Base01Controller {
	
	@Autowired
	private Base01ProcedureInvokerRepository base01ProcedureInvokerRepository; 
	
	@GetMapping("/obtenerOpciones/{ruc}")
	public Respuesta<List<OpcionDTO>> obtenerOpciones(@PathVariable(value = "ruc") String ruc) {
		return base01ProcedureInvokerRepository.obtenerOpciones(ruc);
	}
	
}
