package pe.gob.osce.rnp.seg.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.CoPaisProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.ListadoDatosIdentifiacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping("/api/coPais")
public class CoPaisController {
	
	@Autowired
	private CoPaisProcedureInvokerRepository coPaisProcedureInvokerRepository; 
	
	@GetMapping("/obtenerDatosIdentificacion/{indTipo}")
	public ResponseDTO obtenerDatosIdentificacion(@PathVariable(value = "indTipo") String indTipo) {
		Optional<List<ListadoDatosIdentifiacionDTO>> optionalLst = Optional.ofNullable(coPaisProcedureInvokerRepository.obtenerDatosIdentificacion(indTipo));
		if(optionalLst.isPresent()){
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, optionalLst.get());
		}
		return new ResponseDTO(Enums.ResponseCode.EMPTY_RESPONSE.get(), false, optionalLst.get());
	}
	
}
