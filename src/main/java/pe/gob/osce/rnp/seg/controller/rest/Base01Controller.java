package pe.gob.osce.rnp.seg.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.DatosIdentificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ListadoCorreosProvExtDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ListadoEmpresaExtDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping("/api/base01")
public class Base01Controller {
	
	@Autowired
	private Base01ProcedureInvokerRepository base01ProcedureInvokerRepository; 
	
	@GetMapping("/obtenerOpciones/{ruc}")
	public ResponseDTO obtenerOpciones(@PathVariable(value = "ruc") String ruc) {
		Optional<List<OpcionDTO>> optionalLst = Optional.ofNullable(base01ProcedureInvokerRepository.obtenerOpciones(ruc));
		if(optionalLst.isPresent()){
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, optionalLst.get());
		}
		return new ResponseDTO(Enums.ResponseCode.EMPTY_RESPONSE.get(), false, optionalLst.get());
	}
	
	@PostMapping("/registrarCorreo")
	public ResponseDTO registrarCorreo(@RequestParam(value = "ruc") String ruc, 
			@RequestParam(value = "correo") String correo, @RequestParam(value = "correoConfirmacion") String correoConfirmacion) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, base01ProcedureInvokerRepository.registrarCorreo(ruc, correo, correoConfirmacion));
	}
	
	@PostMapping("/registrarMensaje")
	public ResponseDTO registrarMensaje(@RequestParam(value = "ruc") String ruc, 
			@RequestParam(value = "idAsunto") String idAsunto, @RequestParam(value = "desMensaje") String desMensaje) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, base01ProcedureInvokerRepository.registrarMensaje(ruc, idAsunto, desMensaje));
	}
	
	@GetMapping("/validaCorreoExtNoDom/{correo}")
	public ResponseDTO validaCorreoExtNoDom(@PathVariable(value = "correo") String correo) {
		Optional<List<ListadoCorreosProvExtDTO>> optionalLst = Optional.ofNullable(base01ProcedureInvokerRepository.validaCorreoExtNoDom(correo));
		if(optionalLst.isPresent()){
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, optionalLst.get());
		}
		return new ResponseDTO(Enums.ResponseCode.EMPTY_RESPONSE.get(), false, optionalLst.get());
	}
	
	@GetMapping("/validaCorreoRepExtNoDom")
	public ResponseDTO validaCorreoRepExtNoDom(@RequestParam(value = "correo") String correo) {
		Optional<List<ListadoCorreosProvExtDTO>> optionalLst = Optional.ofNullable(base01ProcedureInvokerRepository.validaCorreoRepExtNoDom(correo));
		if(optionalLst.isPresent()){
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, optionalLst.get());
		}
		return new ResponseDTO(Enums.ResponseCode.EMPTY_RESPONSE.get(), false, optionalLst.get());
	}
	
	@PostMapping("/validarDatosIdentificacion")
	public ResponseDTO validarDatosIdentificacion(@ModelAttribute DatosIdentificacionDTO datosIdentificacionDto) {
		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, base01ProcedureInvokerRepository.validarDatosIdentificacion(datosIdentificacionDto));
	}
	
	@GetMapping("/validaEmpresaExtNoDom/{codPais}/{indPnp}/{razonSocial}")
	public ResponseDTO validaEmpresaExtNoDom(@PathVariable(value = "codPais") String correo, @PathVariable(value = "indPnp") Integer indPnp, @PathVariable(value = "razonSocial") String razonSocial) {
		Optional<List<ListadoEmpresaExtDTO>> optionalLst = Optional.ofNullable(base01ProcedureInvokerRepository.validaEmpresaExtNoDom(correo, indPnp, razonSocial));
		if(optionalLst.isPresent()){
			return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, optionalLst.get());
		}
		return new ResponseDTO(Enums.ResponseCode.EMPTY_RESPONSE.get(), false, optionalLst.get());
	}

//	@PostMapping("/obtenerMensaje")
//	public ResponseDTO obtenerMensaje(@ModelAttribute MensajeCuerpoDTO mensajeCuerpoDto) {
//		return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, base01ProcedureInvokerRepository.obtenerMensaje(mensajeCuerpoDto));
//	}
		
}
