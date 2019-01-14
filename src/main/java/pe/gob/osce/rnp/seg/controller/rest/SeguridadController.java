package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.svc.SeguridadService;
import pe.gob.osce.rnp.seg.utils.Enums;

@RestController
@RequestMapping("/api/seg")
public class SeguridadController {

    private SeguridadService seguridadService;

    @Autowired
    public SeguridadController(SeguridadService seguridadService){
        this.seguridadService = seguridadService;
    }

    @PostMapping("/login")
    public Respuesta<String> login(@RequestParam(value = "ruc") Long ruc, @RequestParam(value = "clave") String clave){
        if(ruc != null && clave != null && clave.length()>7)
            return seguridadService.validarIngreso(ruc, clave);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

    @GetMapping("/validar/cod-verificacion/{ruc}/{cod}")
    public Respuesta<String> validarCodVerificacion(
        @PathVariable(value = "ruc") Long ruc,
        @PathVariable(value = "cod") Long cod){
        if(ruc != null && cod != null)
            return seguridadService.validarCodVerificacion(ruc, cod);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

    @PostMapping("/actualizar/clave")
    public Respuesta<String> actualizarClaveProveedor(
            @RequestParam(value = "ruc") Long ruc,
            @RequestParam(value = "clave") String clave,
            @RequestParam(value = "correo") String correo){
        if(ruc != null && clave != null && correo != null)
            return seguridadService.actualizarClave(ruc, clave, correo);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }
}
