package pe.gob.osce.rnp.seg.dao;

public interface SeguridadProcedureInvokerRepository {

    Boolean validarCodVer(String ruc, String codVer);

    Boolean validarClave(String ruc);

    Boolean actualizarClave(String ruc, String nuevaClave);

    Boolean validarIngreso(String toString, String clave);
}
