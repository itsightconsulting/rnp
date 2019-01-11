package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.PreCorreoDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.svc.ProveedorService;
import pe.gob.osce.rnp.seg.utils.Enums;

import javax.validation.Valid;

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
    public Respuesta<OpcionDTO> obtenerOpciones(@PathVariable(value = "ruc") String ruc){
        return proveedorService.obtenerOpciones(ruc);
    }

    @GetMapping("/recuperar-pass/sc/obtener/correo/{ruc}")
    public Respuesta<String> obtenerDireccionDeCorreo(@PathVariable(value = "ruc") String ruc){
        return proveedorService.obtenerCorreo(ruc);
    }

    @PostMapping("/recuperar-pass/sc/enviar/correo")
    public Respuesta<String> enviarCorreo(@ModelAttribute @Valid PreCorreoDTO preCorreoDTO, BindingResult bindingResult){
        if(!bindingResult.hasErrors())
            return proveedorService.enviarCorreo(preCorreoDTO);
        bindingResult.getFieldErrors().stream().forEach(err-> System.out.println(err.toString()));
        return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Los datos ingresados son inválidos");
    }
}
