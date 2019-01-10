package pe.gob.osce.rnp.seg.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.dao.ContactoProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.PreCorreoDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.svc.ProveedorService;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private Base01ProcedureInvokerRepository base01ProcedureInvokerRepository;

    private ContactoProcedureInvokerRepository contactoProcedureInvokerRepository;

    private ProveedorService proveedorService;

    @Autowired
    public ProveedorController(
            Base01ProcedureInvokerRepository base01ProcedureInvokerRepository,
            ContactoProcedureInvokerRepository contactoProcedureInvokerRepository,
            ProveedorService proveedorService){
        this.base01ProcedureInvokerRepository = base01ProcedureInvokerRepository;
        this.contactoProcedureInvokerRepository = contactoProcedureInvokerRepository;
        this.proveedorService = proveedorService;
    }

    @GetMapping("/recuperar-pass/sc/obt/opciones/{ruc}")
    public Respuesta<OpcionDTO> obtenerOpciones(@PathVariable(value = "ruc") String ruc){
        return proveedorService.obtenerOpciones(ruc);
    }

    @GetMapping("/recuperar-pass/sc/obtener/correo/${ruc}")
    public Respuesta<String> obtenerDireccionDeCorreo(@PathVariable(value = "ruc") String ruc){
        return contactoProcedureInvokerRepository.obtenerCorreo(ruc);
    }

    @PostMapping("/recuperar-pass/sc/enviar/correo/${ruc}")
    public Respuesta<String> enviarCorreo(@ModelAttribute PreCorreoDTO preCorreoDTO){
        //return contactoProcedureInvokerRepository.enviarCorreo(preCorreoDTO);
        return null;
    }


}
