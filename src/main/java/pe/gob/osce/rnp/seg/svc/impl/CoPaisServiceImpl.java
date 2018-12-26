package pe.gob.osce.rnp.seg.svc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import pe.gob.osce.rnp.seg.dao.CoPaisRepository;
import pe.gob.osce.rnp.seg.model.jpa.CoPais;
import pe.gob.osce.rnp.seg.svc.CoPaisService;

@Service
public class CoPaisServiceImpl implements CoPaisService{

	
    @Autowired
    private DataSource dataSource;

	@Autowired
	private CoPaisRepository coPaisRepository;
////	@GetMapping(value = "/registrar/{descripcion}")
//    public @ResponseBody String registrar(@PathVariable(value = "descripcion") String string) {
//		System.out.println("CONTROLADOR");
//        final SimpleJdbcCall updateEmployeeCall = new SimpleJdbcCall(dataSource).withFunctionName("sp_insertarPais");
//        final Map<String, Object> params = new HashMap<>();
////        params.put("p_id", id);
//        params.put("description", string);
//
//        final Map<String, Object> result = updateEmployeeCall.execute(params);
//        System.out.println(result.get("returnvalue"));
//        return "1";//1:Éxito
//    	
//    }

	
	@Override
	public CoPais save(CoPais entity) {
		
		return entity;
	}

	@Override
	public CoPais update(CoPais entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CoPais findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CoPais findOneWithFT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CoPais> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String registrar(String string) {
		
//		coPaisRepository.insertPais(string);
//		Integer res1 = (Integer) proc.getOutputParameterValue("res1");
        return "1";//1:Éxito

		
	}
	
	public String eliminar(int id) {
		
//		coPaisRepository.eliminar(id);
//		System.out.println("ESTA ELIMINANDO");
//        final SimpleJdbcCall updateEmployeeCall = new SimpleJdbcCall(dataSource).withFunctionName("sp_eliminarPais");
//        final Map<String, Object> params = new HashMap<>();
////        params.put("p_id", id);
//        params.put("id", id);
//
//        final Map<String, Object> result = updateEmployeeCall.execute(params);
//        System.out.println(result.get("returnvalue"));
        return "1";//1:Éxito

	}

	@Override
	public CoPais buscar(int id) {
		
		CoPais co = new CoPais();
		System.out.println("LISTANDO");
        final SimpleJdbcCall updateEmployeeCall = new SimpleJdbcCall(dataSource).withFunctionName("sp_buscarPais");
        final Map<String, Object> params = new HashMap<>();
//        params.put("p_id", id);
        params.put("id", id);
        System.out.println("PARAMETRO ENTRADA__" + id);
//        co.setCodPais(id);
        
        final Map<String, Object> result = updateEmployeeCall.execute(params);
        System.out.println("RESULT" + result.toString());
        System.out.println(result.get("returnvalue"));
        return co;//1:Éxito

	}

}
