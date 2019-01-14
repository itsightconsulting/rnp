package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osce.rnp.seg.constants.StoredProcedureName;
import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.utils.Enums;
import pe.gob.osce.rnp.seg.utils.Validador;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Base01ProcedureInvokerImpl implements Base01ProcedureInvokerRepository {

    public static final Logger LOGGER = LogManager.getLogger(Base01ProcedureInvokerImpl.class);

    private EntityManager entityManager;

    @Autowired
    public Base01ProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Respuesta obtenerOpciones(String ruc) {
        StoredProcedureQuery spQuery;
        if(Validador.validarUsuario(Long.valueOf(ruc))) {
            spQuery = entityManager.createStoredProcedureQuery(StoredProcedureName.SP_EXISTE_PROVEEDOR);
            spQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
            spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

            spQuery.setParameter("ruc", ruc);
            spQuery.execute();
            // Obtenemos los valores de salida
            boolean existeRuc = spQuery.getOutputParameterValue("respuesta") == "1";
            if(existeRuc){
                spQuery = entityManager.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_OPCS_P_CAMBIO_PASS);
                // Registrar los parámetros de entrada y salida
                spQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
                spQuery.setParameter("ruc", ruc);
                List<Object[]> resultSet = spQuery.getResultList();

                int size = resultSet.size();
                if(size > 0){
                    OpcionDTO opcs = new OpcionDTO();
                    resultSet.forEach(x-> {
                        opcs.setOpcion1(String.valueOf(x[0]));
                        opcs.setOpcion2(String.valueOf(x[1]));
                        opcs.setOpcion3(String.valueOf(x[2]));
                        opcs.setOpcion4(String.valueOf(x[3]));
                    });
                    return new Respuesta(Enums.ResponseCode.EXITO_GENERICA.get(), 1, opcs);
                }
            }
            LOGGER.info("Resul set length: 0");
            return new Respuesta(Enums.ResponseCode.EMPTY_RESPONSE.get(), 0);
        }
        LOGGER.info("Ruc inválido");
        return new Respuesta(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

}