package pe.gob.osce.rnp.seg.controller.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.model.jpa.dto.UpdClaveDto;
import pe.gob.osce.rnp.seg.svc.SeguridadService;
import pe.gob.osce.rnp.seg.utils.Enums;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "${api.bs.route}/seg")
public class SeguridadController {

    public static final Logger LOGGER = LogManager.getLogger(SeguridadController.class);

    private SeguridadService seguridadService;

    @Autowired
    public SeguridadController(SeguridadService seguridadService){
        this.seguridadService = seguridadService;
    }

    @GetMapping("/d/a")
    public String demoEndPoint(){
        return "{\"test\": \"successfully\"}";
    }

    @PostMapping("/login")
    public Respuesta<String> login(@RequestParam(value = "ruc") Long ruc, @RequestParam(value = "clave") String clave){
        if(ruc != null && clave != null && clave.length()>0)
            return seguridadService.validarIngreso(ruc, clave);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El usuario y/o clave inválidos");
    }

    @GetMapping("/validar/cod-verificacion/{ruc}/{cod}")
    public Respuesta<String> validarCodVerificacion(
        @PathVariable(value = "ruc") Long ruc,
        @PathVariable(value = "cod") Long cod){
        if(ruc != null && cod != null)
            return seguridadService.validarCodVerificacion(ruc, cod);
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Validación fallida");
    }

    @PostMapping("/actualizar/clave")
    public Respuesta<String> actualizarClaveProveedor(@ModelAttribute @Valid UpdClaveDto updClaveDto, BindingResult bindingResult){
        if(!bindingResult.hasErrors())
            return seguridadService.actualizarClave(updClaveDto);
        bindingResult.getFieldErrors().stream().forEach(err-> LOGGER.info(err.toString()));
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Los datos ingresados son inválidos");
    }
}
