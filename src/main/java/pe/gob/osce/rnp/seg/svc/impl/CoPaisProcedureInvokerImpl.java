package pe.gob.osce.rnp.seg.svc.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.dao.CoPaisProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.DatosIdentifiacionDTO;

@Service
public class CoPaisProcedureInvokerImpl implements CoPaisProcedureInvokerRepository {

    private EntityManager entityManager;

    @Autowired
    public CoPaisProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public List<DatosIdentifiacionDTO> obtenerDatosIdentificacion(String indTipo) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobtenerdatosidentif");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_IND_TIPO", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("C_IND_TIPO", indTipo);
        List<Object[]> resultSet = storedProcedureQuery.getResultList();
        int size = resultSet.size();
	        if(size > 0){
	            List<DatosIdentifiacionDTO> opciones = new ArrayList<>(resultSet.size());
	            resultSet.forEach(x-> opciones.add(new DatosIdentifiacionDTO(String.valueOf(x[0]), String.valueOf(x[1]))));
	            return opciones;
	        }else{
	            System.out.println(">>EMPTY RESULT SET");
	        }
    
        return null;
        
	}
	
}
