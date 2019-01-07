package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.Opcion;
import pe.gob.osce.rnp.seg.utils.Validador;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class Base01ProcedureInvokerImpl implements Base01ProcedureInvokerRepository {

    private EntityManager entityManager;

    @Autowired
    public Base01ProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Opcion> obtenerOpciones(String ruc) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobteneropciones");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
        if(Validador.validRuc(ruc)) {
            System.out.println("valor " + Validador.validRuc(ruc));
            storedProcedureQuery.setParameter("ruc", ruc);
            List<Object[]> resultSet = storedProcedureQuery.getResultList();
            int size = resultSet.size();
            if(size > 0){
                List<Opcion> opciones = new ArrayList<>(resultSet.size());
                resultSet.forEach(x-> opciones.add(new Opcion(String.valueOf(x[0]), String.valueOf(x[1]), String.valueOf(x[2]), String.valueOf(x[3]))));
                return opciones;
            }else{
                System.out.println(">>EMPTY RESULT SET");
            }
        }else {
            System.out.println("valor " + Validador.validRuc(ruc));
            System.out.println("Fallo la validación");
        }

        return null;

    }

}