package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.generic.BaseService;
import pe.gob.osce.rnp.seg.model.jpa.Parametro;

public interface ParametroService extends BaseService<Parametro> {

    Parametro findByClave(String parametro);
}
