package pe.gob.osce.rnp.seg.svc.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.axis2.databinding.types.xsd.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.dao.Base01ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.dto.CorreosProvExtDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.DatosIdentificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.EmpresaExtDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.utils.Validador;

@Service
public class Base01ProcedureInvokerImpl implements Base01ProcedureInvokerRepository {

    private EntityManager entityManager;

    @Autowired
    public Base01ProcedureInvokerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public List<OpcionDTO> obtenerOpciones(String ruc) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spobteneropciones");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("ruc", String.class, ParameterMode.IN);
        if(Validador.validRuc(ruc)) {
	     	System.out.println("valor " + Validador.validRuc(ruc));
	        storedProcedureQuery.setParameter("ruc", ruc);
	        List<Object[]> resultSet = storedProcedureQuery.getResultList();
	        int size = resultSet.size();
		        if(size > 0){
		            List<OpcionDTO> opciones = new ArrayList<>(resultSet.size());
		            resultSet.forEach(x-> opciones.add(new OpcionDTO(String.valueOf(x[0]), String.valueOf(x[1]), String.valueOf(x[2]), String.valueOf(x[3]))));
		            return opciones;
		        }else{
		            System.out.println(">>EMPTY RESULT SET");
		        }
        }else {
			System.out.println("valor " + Validador.validRuc(ruc));
			System.out.println("Fallo en la transacción");
		}    
        return null;        
	}

	@Override
	public Mensaje registrarCorreo(String ruc, String correo, String correoConfirmacion) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spregistrarcorreo");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_CORREO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_CORREOCONFIRMA", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validRuc(ruc)) {
	     	System.out.println("valor " + Validador.validRuc(ruc));
	        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
	        storedProcedureQuery.setParameter("C_DES_CORREO", correo);
        	storedProcedureQuery.setParameter("C_DES_CORREOCONFIRMA", correoConfirmacion);
        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos los valores de salida
        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
        return new Mensaje(outputValue1,outputValue2);
        }else {
			System.out.println("valor " + Validador.validRuc(ruc));
			System.out.println("Fallo en la transacción");
		}            
        return new Mensaje();
	}

	@Override
	public Mensaje registrarMensaje(String ruc, String idAsunto, String desMensaje) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spregistrarmensaje");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("N_ID_ASUNTO", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_MENSAJE", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validRuc(ruc)) {
	     	System.out.println("valor " + Validador.validRuc(ruc));
	        storedProcedureQuery.setParameter("C_DES_RUC", ruc);
	        storedProcedureQuery.setParameter("N_ID_ASUNTO", idAsunto);
        	storedProcedureQuery.setParameter("C_DES_MENSAJE", desMensaje);
        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos los valores de salida
        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
        return new Mensaje(outputValue1,outputValue2);
        }else {
			System.out.println("valor " + Validador.validRuc(ruc));
			System.out.println("Fallo en la transacción");
		}            
        return new Mensaje();
	}

	@Override
	public List<CorreosProvExtDTO> validaCorreoExtNoDom(String correo) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidarcorreoextnodom");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("CORREO", String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("CORREO", correo);
        List<Object[]> resultSet = storedProcedureQuery.getResultList();
        int size = resultSet.size();
	        if(size > 0){
	            List<CorreosProvExtDTO> opciones = new ArrayList<>(resultSet.size());
	            resultSet.forEach(x-> opciones.add(new CorreosProvExtDTO(String.valueOf(x[0]), String.valueOf(x[1]))));
	            return opciones;
	        }else{
	            System.out.println(">>EMPTY RESULT SET");
	        }
       
        return null;        
	}
	
	@Override
	public List<CorreosProvExtDTO> validaCorreoRepExtNoDom(String correo) {
	    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidarcorreorepextnodom");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("CORREO", String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("CORREO", correo);
        List<Object[]> resultSet = storedProcedureQuery.getResultList();
        int size = resultSet.size();
	        if(size > 0){
	            List<CorreosProvExtDTO> opciones = new ArrayList<>(resultSet.size());
	            resultSet.forEach(x-> opciones.add(new CorreosProvExtDTO(String.valueOf(x[0]), String.valueOf(x[1]))));
	            return opciones;
	        }else{
	            System.out.println(">>EMPTY RESULT SET");
	        }
       
        return null;        
	}

	@Override
	public Mensaje validarDatosIdentificacion(DatosIdentificacionDTO datosIdentificacionDto) {
	StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidardatosidentificacion");
	    
        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_RUC", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_ID_PAIS", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_ID_TIPODOCU", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_DES_DOCU", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("N_ID_ZONAREGISTRAL", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_NRO_PARTIDA", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("D_FEC_INGRESO", DateTime.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_ID_TIPOCONDICION", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        // Configuramos el valor de entrada
        if(Validador.validRuc(datosIdentificacionDto.getRuc())) {
	     	System.out.println("valor " + Validador.validRuc(datosIdentificacionDto.getRuc()));
	        storedProcedureQuery.setParameter("C_DES_RUC", datosIdentificacionDto.getRuc());
	        storedProcedureQuery.setParameter("C_ID_PAIS", datosIdentificacionDto.getIdPais());
	        storedProcedureQuery.setParameter("C_ID_TIPODOCU", datosIdentificacionDto.getIdTipoDocu());
	        storedProcedureQuery.setParameter("C_DES_DOCU", datosIdentificacionDto.getDesDocu());
	        storedProcedureQuery.setParameter("N_ID_ZONAREGISTRAL", datosIdentificacionDto.getIdZonaRegistral());
	        storedProcedureQuery.setParameter("C_NRO_PARTIDA", datosIdentificacionDto.getNroPartida());
	        storedProcedureQuery.setParameter("D_FEC_INGRESO", datosIdentificacionDto.getFecIngreso());
	        storedProcedureQuery.setParameter("C_ID_TIPOCONDICION", datosIdentificacionDto.getIdTipoCondicion());

        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos los valores de salida
        String outputValue1 = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
        String outputValue2 = (String) storedProcedureQuery.getOutputParameterValue("respuesta");
        System.out.println("OUT1: "+ outputValue1+ " | OUT2: "+outputValue2);
        return new Mensaje(outputValue1,outputValue2);
        }else {
			System.out.println("valor " + Validador.validRuc(datosIdentificacionDto.getRuc()));
			System.out.println("Fallo en la transacción");
		}            
        return new Mensaje();
	}

	@Override
	public List<EmpresaExtDTO> validaEmpresaExtNoDom(String codPais, Integer indPnp, String razonSocial) {
	   StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spvalidarempresaextnodom");

	        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("C_COD_PAIS", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("N_IND_PNPJ", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("C_NOM_RAZSOCIAL", String.class, ParameterMode.IN);
        
        storedProcedureQuery.setParameter("C_COD_PAIS", codPais);
        storedProcedureQuery.setParameter("N_IND_PNPJ", indPnp);
        storedProcedureQuery.setParameter("C_NOM_RAZSOCIAL", razonSocial);
        List<Object[]> resultSet = storedProcedureQuery.getResultList();
        int size = resultSet.size();
	        if(size > 0){
	            List<EmpresaExtDTO> opciones = new ArrayList<>(resultSet.size());
	            resultSet.forEach(x-> opciones.add(new EmpresaExtDTO(String.valueOf(x[0]), String.valueOf(x[1]))));
	            return opciones;
	        }else{
	            System.out.println(">>EMPTY RESULT SET");
	        }
        return null;        
	}

//	@Override
//	public String obtenerMensaje(MensajeCuerpoDTO mensajeCuerpoDto) {
//			String query = "SELECT dbo.fnobtenermensaje(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)";
//			Query nativeQuery = entityManager.createNativeQuery(query);
//			nativeQuery.setParameter(1, mensajeCuerpoDto.getIndTipo());//Integer
//			nativeQuery.setParameter(2, "");//VARCHAR
////			nativeQuery.setParameter(3, new DateTime());//DATETIME
//			nativeQuery.setParameter(4, mensajeCuerpoDto.getCodVerificacion());//VARCHAR(6)
//			nativeQuery.setParameter(5, null);//OBS 1 VARCHAR NULLABLE
//			nativeQuery.setParameter(6, null);//OBS 2 VARCHAR NULLABLE
//			nativeQuery.setParameter(7, null);//OBS 3 VARCHAR NULLABLE
//			nativeQuery.setParameter(8, null);//OBS 4 VARCHAR NULLABLE
//			Optional<Object> opt = Optional.ofNullable(nativeQuery.getSingleResult());
//			return opt.isPresent() ? opt.get().toString() : null;
//	}
	
	
}
