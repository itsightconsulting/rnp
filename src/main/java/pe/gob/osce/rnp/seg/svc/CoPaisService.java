package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.generic.BaseService;
import pe.gob.osce.rnp.seg.model.jpa.CoPais;

public interface CoPaisService extends BaseService<CoPais>{

	public String registrar(String string);
	public String eliminar(int id);
	public CoPais buscar(int id);
}
