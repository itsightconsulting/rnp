package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.PrmClaConfiguracion;

@Repository
public interface PrmClaConfiguracionRepository extends JpaRepository<PrmClaConfiguracion, Integer>{

    @Transactional
    @Procedure(name = "sp_registrarcodverificacion")
    Mensaje insertCodVerificacion(
    		@Param("C_DES_RUC") String desRuc, 
    		@Param("C_DES_CORREO") String desCorreo, 
    		@Param("C_COD_UID") String codUid, 
    		@Param("C_DES_IP") String desIp, 
    		@Param("C_DES_CODVERIFICACION") String desCodVerificacion);		
}
