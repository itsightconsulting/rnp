package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvoker;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.svc.ProveedorService;
import pe.gob.osce.rnp.seg.utils.Enums;
import pe.gob.osce.rnp.seg.utils.Validador;

import java.util.Optional;


@Service
@Transactional
public class ProveedorServiceImpl extends BaseServiceImpl<ProveedorProcedureInvoker> implements ProveedorService {

    public static final Logger LOGGER = LogManager.getLogger(ProveedorServiceImpl.class);


    public ProveedorServiceImpl(ProveedorProcedureInvoker repository) {
        super(repository);
    }

    @Override
    public Respuesta<OpcionDTO> obtenerOpciones(String ruc) {
        if(Validador.validRuc(ruc)) {
            boolean existeRuc = repository.validarExistencia(ruc);
            if(existeRuc){
                Optional<OpcionDTO> optOpc= Optional.ofNullable(repository.obtenerOpcionesCambioPassword(ruc));
                if(optOpc.isPresent())
                    return new Respuesta(Enums.ResponseCode.EXITO_GENERICA.get(), 1, optOpc.get());
            }
            LOGGER.info("Resul set length: 0");
            return new Respuesta(Enums.ResponseCode.EMPTY_RESPONSE.get(), 0);
        }
        LOGGER.info("Ruc inválido");
        return new Respuesta(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }
}
