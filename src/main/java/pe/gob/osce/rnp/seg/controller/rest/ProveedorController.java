package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.model.jpa.dto.*;
import pe.gob.osce.rnp.seg.svc.ProveedorService;
import pe.gob.osce.rnp.seg.utils.Enums;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

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
    public Respuesta<String> enviarCorreo(@ModelAttribute @Valid PreCorreoDTO preCorreoDTO, BindingResult bindingResult){
        if(!bindingResult.hasErrors())
            return proveedorService.enviarCorreo(preCorreoDTO);
        bindingResult.getFieldErrors().stream().forEach(err-> System.out.println(err.toString()));
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Los datos ingresados son inválidos");
    }

    @PostMapping("/recuperar-pass/sc/validar/datos-identificacion")
    public Respuesta<String> validarDatosIdentificacion(@ModelAttribute @Valid DatosIdentificacionDTO dtsIdentificacion, BindingResult bindingResult){
        if(!bindingResult.hasErrors())
            return proveedorService.validarDatosIdentificacion(dtsIdentificacion);
        bindingResult.getFieldErrors().stream().forEach(err-> System.out.println(err.toString()));
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Los datos ingresados son inválidos");
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

    @PostMapping("/recuperar-pass/su/enviar/correo/ext-nodom")
    public Respuesta<String> enviarCorreoProvExtNoDom(@RequestParam(value = "correo") String correo){
        return proveedorService.enviarCorreoProvExtNoDom(correo);
    }

    @PostMapping("/recuperar-pass/su/enviar/correo/rep-ext-nodom")
    public Respuesta<String> enviarCorreoRepProExtNoDom(@RequestParam(value = "correo") String correo){
        return proveedorService.enviarCorreoRepProvExtNoDom(correo);
    }

    @GetMapping("/recuperar-pass/su/obtener/listado/empresa-ext-no-dom")
    public Respuesta<List<ProExtNoDom>> obtenerListadoEmpresaExtNoDom(
            @RequestParam(value = "paisId") String paisId,
            @RequestParam(value = "tipoPersonaId") Integer tipoPersonaId,
            @RequestParam(value = "razonSocial") String razonSocial){
        if(paisId != null && paisId.length()>0 && tipoPersonaId != null && tipoPersonaId>0 && razonSocial != null && razonSocial.length()>4)
            return proveedorService.obtenerListadoEmpresasExtNoDom(paisId, tipoPersonaId, razonSocial);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

}
