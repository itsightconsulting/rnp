package pe.gob.osce.rnp.seg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.Opcion;

@RestController
@RequestMapping("/base01")
public class Base01Controller {
	
	@Autowired
	private Base01ProcedureInvokerRepository base01ProcedureInvokerRepository; 
	
	@GetMapping("/obtenerOpciones/{ruc}")
	public List<Opcion> obtenerOpciones(@PathVariable(value = "ruc") String ruc) {
		System.out.println("Iniciando sistema");
		return base01ProcedureInvokerRepository.obtenerOpciones(ruc);
	}
	
}
