package pe.gob.osce.rnp.seg.controller.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.model.jpa.dto.*;
import pe.gob.osce.rnp.seg.svc.ProveedorService;
import pe.gob.osce.rnp.seg.utils.Enums;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "${api.bs.route}/proveedor")
public class ProveedorController {

    private static final String MSG_LOG_VALID_FAIL = "Los datos ingresados son inválidos";

    public static final Logger LOGGER = LogManager.getLogger(ProveedorController.class);

    private ProveedorService proveedorService;

    @Autowired
    public ProveedorController(
            ProveedorService proveedorService){
        this.proveedorService = proveedorService;
    }

    @GetMapping("/recuperar-pass/sc/obt/opciones/{ruc}")
    public Respuesta<OpcionDTO> obtenerOpciones(@PathVariable(value = "ruc") Long ruc){
        return proveedorService.obtenerOpciones(ruc);
    }

    @GetMapping("/recuperar-pass/sc/obtener/correo/{ruc}")
    public Respuesta<String> obtenerDireccionCorreo(@PathVariable(value = "ruc") Long ruc){
        return proveedorService.obtenerCorreo(ruc);
    }

    @GetMapping("/recuperar-pass/sc/obtener/listado/correo/rep/{ruc}")
    public Respuesta<List<CorreoRepDTO>> obtenerDireccionCorreoRepresentante(@PathVariable(value = "ruc") Long ruc){
        return proveedorService.obtenerListadoCorreoRepresentante(ruc);
    }

    @PostMapping("/recuperar-pass/sc/enviar/correo")
    public Respuesta<String> enviarCorreo(@ModelAttribute @Valid PreCorreoDTO preCorreoDTO, BindingResult bindingResult, HttpServletRequest request){
        if(!bindingResult.hasErrors()){
            preCorreoDTO.setIpCliente(request.getRemoteAddr());
            return proveedorService.enviarCorreo(preCorreoDTO);
        }
        bindingResult.getFieldErrors().stream().forEach(err-> LOGGER.info(err.toString()));
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_LOG_VALID_FAIL);
    }

    @PostMapping("/recuperar-pass/sc/validar/datos-identificacion")
    public Respuesta<String> validarDatosIdentificacion(@ModelAttribute @Valid DatosIdentificacionDTO dtsIdentificacion, BindingResult bindingResult, HttpServletRequest request){
        if(!bindingResult.hasErrors())
            return proveedorService.validarDatosIdentificacion(dtsIdentificacion, request.getRemoteAddr());
        bindingResult.getFieldErrors().stream().forEach(err-> LOGGER.info(err.toString()));
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_LOG_VALID_FAIL);
    }

    @GetMapping("/recuperar-pass/sc/obtener/foranea/pais")
    public Respuesta<List<ForaneaProveedorDTO>> obtenerListadoPais(){
            return proveedorService.obtenerListadoForaneaProveedor(Enums.Foranea.PAIS.get());
    }

    @GetMapping("/recuperar-pass/sc/obtener/foranea/tipo-documento")
    public Respuesta<List<ForaneaProveedorDTO>> obtenerListadoTipoDocumento(){
        return proveedorService.obtenerListadoForaneaProveedor(Enums.Foranea.TIPO_DOCUMENTO.get());
    }

    @GetMapping("/recuperar-pass/sc/obtener/foranea/zona-registral")
    public Respuesta<List<ForaneaProveedorDTO>> obtenerListadoZonaRegistral(){
        return proveedorService.obtenerListadoForaneaProveedor(Enums.Foranea.ZONA_REGISTRAL.get());
    }

    @GetMapping("/recuperar-pass/sc/obtener/foranea/tipo-condicion")
    public Respuesta<List<ForaneaProveedorDTO>> obtenerListadoTipoCondicion(){
        return proveedorService.obtenerListadoForaneaProveedor(Enums.Foranea.TIPO_CONDICION.get());
    }

    @PutMapping("/recuperar-pass/sc/actualizar/correo/ext-nodom")
    public Respuesta<String> actualizarCorreoRepExtNoDom(
            @RequestParam(value = "ruc") Long ruc,
            @RequestParam(value = "correo") String correo){
        if(ruc != null && correo != null)
            return proveedorService.actualizarCorreoExtNoDom(ruc, correo);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

    @PostMapping("/recuperar-pass/sc/post/sso-sunat-validacion")
    public Respuesta<String> registrarValPreviaActualizacionCorreo(
            @RequestParam(value = "ruc") Long ruc,
            @RequestParam(value = "ipCliente") String ipCliente){
        if(ruc != null && ipCliente != null)
            return proveedorService.registrarValPreviaActualizacionCorreo(ruc, ipCliente);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

    @PostMapping("/recuperar-pass/su/enviar/correo/ext-nodom")
    public Respuesta<String> enviarCorreoProvExtNoDom(@RequestParam(value = "correo") String correo){
        return proveedorService.enviarCorreoProvExtNoDomOrRepProvExtNoDom(correo, 1);
    }

    @PostMapping("/recuperar-pass/su/enviar/correo/rep-ext-nodom")
    public Respuesta<String> enviarCorreoRepProExtNoDom(@RequestParam(value = "correo") String correo){
        return proveedorService.enviarCorreoProvExtNoDomOrRepProvExtNoDom(correo, 2);
    }

    @GetMapping("/recuperar-pass/su/obtener/listado/empresa-ext-no-dom/{razonSocial}")
    public Respuesta<List<ProExtNoDom>> obtenerListadoEmpresaExtNoDom(
            @RequestParam(value = "paisId") String paisId,
            @RequestParam(value = "tipoPersonaId") Integer tipoPersonaId,
            @PathVariable(value = "razonSocial") String razonSocial){
        if(paisId != null && paisId.length()>0 && paisId.length()<4 && tipoPersonaId != null && tipoPersonaId>0 && razonSocial != null && razonSocial.length()>4)
            return proveedorService.obtenerListadoEmpresasExtNoDom(paisId, tipoPersonaId, razonSocial);
        List<ProExtNoDom> lst = new ArrayList<>();
        lst.add(new ProExtNoDom(null, MSG_LOG_VALID_FAIL));
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, lst);
    }
}
