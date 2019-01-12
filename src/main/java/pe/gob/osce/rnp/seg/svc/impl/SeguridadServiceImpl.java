package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.SeguridadProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.svc.SeguridadService;
import pe.gob.osce.rnp.seg.utils.Enums.ResponseCode;
import pe.gob.osce.rnp.seg.utils.Validador;

@Service
public class SeguridadServiceImpl extends BaseServiceImpl<SeguridadProcedureInvokerRepository> implements SeguridadService {

    public static final Logger LOGGER = LogManager.getLogger(SeguridadServiceImpl.class);

    public SeguridadServiceImpl(SeguridadProcedureInvokerRepository repository){
        super(repository);
    }

    @Override
    public Respuesta<String> validarCodVerificacion(Long ruc, Long codVer) {
        try {
            if(Validador.validRuc(ruc)){
                if(Validador.validNumberWithLength(codVer, 6)){
                    boolean exito = repository.validarCodVer(ruc.toString(), codVer.toString());
                    if(exito)
                        return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, "Validación exitosa");
                    LOGGER.info("El código de verificación no se ha encontrado en base de datos");
                    return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
                }
                LOGGER.info("Código de verificación con tamaño inválido o vacio");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> actualizarClave(Long ruc, String clave) {
        if(Validador.validRuc(ruc)){
            if(Validador.validarClave(clave)){
                boolean exito = repository.validarClave(ruc.toString());
                if(exito){
                    exito = repository.actualizarClave(ruc.toString(), clave);
                    if(exito)
                        return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, "La clave ha sido actualizada satisfactoriamente");
                    LOGGER.info("Lamentablemente la clave no ha podido ser actualizada. Intentarlo más tarde");
                    return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
                }
            }
            LOGGER.info("Clave tiene un formato inválido o vacio");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        }
        LOGGER.info("Ruc inválido");
        return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }
}
