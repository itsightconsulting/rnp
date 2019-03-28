package pe.gob.osce.rnp.seg.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osce.rnp.seg.constants.StoredProcedureName;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.dto.*;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;
import pe.gob.osce.rnp.seg.utils.Parseador;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ProveedorProcedureInvokerImpl implements ProveedorProcedureInvokerRepository {

    private static final String P_MENSAJE = "MENSAJE";
    private static final String P_RESPUESTA = "RESPUESTA";
    private static final String P_C_DES_RUC = "C_DES_RUC";
    private static final String P_C_DES_CORREO = "C_DES_CORREO";
    private static final String P_CORREO = "CORREO";
    private static final String P_C_DES_IP = "C_DES_IP";

    @Autowired
    private EntityManager em;

    @Override
    public Boolean validarExistencia(String ruc) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_EXISTE_PROVEEDOR);
        spQuery.registerStoredProcedureParameter("RUC", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.setParameter("RUC", ruc);
        spQuery.execute();
        return spQuery.getOutputParameterValue(P_RESPUESTA).equals("1");
    }

    @Override
    public OpcionDTO obtenerOpcionesCambioPassword(String ruc) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_OPCS_P_CAMBIO_PASS);
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
            return opcs;
        }
        return null;
    }

    @Override
    public String obtenerCorreo(String ruc) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_CORREO_PROVEEDOR);
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.execute();
        boolean existeCorreo = spQuery.getOutputParameterValue(P_RESPUESTA).equals("1");
        return existeCorreo ? spQuery.getOutputParameterValue(P_MENSAJE).toString() : null;
    }

    @Override
    public List<CorreoRepDTO> obtenerListadoCorreoRepresentante(String ruc) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_CORREO_REPRESENTANTE);
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.setParameter(P_C_DES_RUC, ruc);
        List<Object[]> result = spQuery.getResultList();
        if(!result.isEmpty()){
            List<CorreoRepDTO> lstCorreosRep = new ArrayList<>();
            result.forEach(x->lstCorreosRep.add(new CorreoRepDTO(x[0].toString(), x[1].toString())));
            return lstCorreosRep;
        }
        return Collections.emptyList();
    }

    @Override
    public Boolean registrarValPreviaActulizacionCorreo(String ruc, String ipCliente) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_REGISTRAR_AUD_ACT_CORREO);

        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_C_DES_IP, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.setParameter(P_C_DES_IP, ipCliente);

        spQuery.execute();
        return spQuery.getOutputParameterValue("respuesta").equals("1");
    }

    @Override
    public String obtenerCodigoVerificacion(String ruc, String correo, String ipCliente) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_REGISTRAR_COD_VERIFICACION);

        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_C_DES_CORREO, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_C_DES_IP, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter("respuesta", String.class, ParameterMode.OUT);

        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.setParameter(P_C_DES_CORREO, correo);
        spQuery.setParameter(P_C_DES_IP, ipCliente);

        spQuery.execute();
        boolean existeRuc = spQuery.getOutputParameterValue("respuesta").equals("1");
        return existeRuc ? spQuery.getOutputParameterValue("mensaje").toString() : null;
    }

    @Override
    public ContenidoCorreoPOJO obtenerContenidoCorreoByTipo(int tipo, String ruc, String codVerificacion, String obs1, String expiration) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_BODY_CORREO);

        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter("N_IND_TIPO", Integer.class, ParameterMode.IN);//Integer
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);//VARCHAR
        spQuery.registerStoredProcedureParameter("D_FEC_CAMBIO", Date.class, ParameterMode.IN);//DATETIME
        spQuery.registerStoredProcedureParameter("C_COD_VERIFIC", String.class, ParameterMode.IN);//VARCHAR(6)
        spQuery.registerStoredProcedureParameter("C_DES_OBSERV1", String.class, ParameterMode.IN);//OBS 1 VARCHAR NULLABLE
        spQuery.registerStoredProcedureParameter("C_DES_OBSERV2", String.class, ParameterMode.IN);//OBS 2 VARCHAR NULLABLE
        spQuery.registerStoredProcedureParameter("C_DES_OBSERV3", String.class, ParameterMode.IN);//OBS 3 VARCHAR NULLABLE
        spQuery.registerStoredProcedureParameter("C_DES_OBSERV4", String.class, ParameterMode.IN);//OBS 4 VARCHAR NULLABLE

        spQuery.setParameter("N_IND_TIPO", tipo);
        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.setParameter("D_FEC_CAMBIO", new Date());
        spQuery.setParameter("C_COD_VERIFIC", codVerificacion);
        spQuery.setParameter("C_DES_OBSERV1", obs1);
        if(expiration != null) {
            String rucEncode = Parseador.getEncodeHash32Id("its_rnp_seg_mod", Long.valueOf(ruc));
            String codeEncode = Parseador.getEncodeHash32Id("its_rnp_seg_mod", Long.valueOf(codVerificacion));
            StringBuilder sb = new StringBuilder(1000);
            sb.append("?de=");
            sb.append(expiration);
            sb.append("&key=");
            sb.append(rucEncode);
            sb.append("&cd=");
            sb.append(codeEncode);
            spQuery.setParameter("C_DES_OBSERV4", sb.toString());
        }
        List<Object[]> result = spQuery.getResultList();
        if(!result.isEmpty()){
            return new ContenidoCorreoPOJO(
                    Integer.parseInt(result.get(0)[0].toString()),//AsuntoId
                    result.get(0)[1].toString(),//NombreAsunto
                    result.get(0)[2] == null ? "Correo nulo" : result.get(0)[2].toString());
        }
        return null;
    }

    @Override
    public String registrarCorreoEnviado(String ruc, Integer idAsunto, String cuerpoCorreo) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_REGISTRAR_CORREO_ENVIADO);

        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("N_ID_ASUNTO", Integer.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_DES_MENSAJE", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.setParameter("N_ID_ASUNTO", idAsunto);
        spQuery.setParameter("C_DES_MENSAJE", cuerpoCorreo);
        spQuery.execute();
        return spQuery.getOutputParameterValue(P_RESPUESTA).toString();
    }

    @Override
    public List<ForaneaProveedorDTO> obtenerListadoForanea(String tipo) {
        List<ForaneaProveedorDTO> lstForanea = new ArrayList<>();

        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_OBTENER_FORANEAS_PROVEEDOR);
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter("C_IND_TIPO", String.class, ParameterMode.IN);//Integer
        spQuery.setParameter("C_IND_TIPO", tipo);
        List<Object[]> result = spQuery.getResultList();
        if(!result.isEmpty()) {
            result.forEach(x -> lstForanea.add(new ForaneaProveedorDTO(x[0].toString(), x[1].toString())));
            return lstForanea;
        }
        return Collections.emptyList();
    }

    @Override
    public Boolean validarDatosIdentificacion(DatosIdentificacionDTO dtsIdentificacion) {
        System.out.println(dtsIdentificacion.toString());
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_VALIDAR_DATOS_IDEN_PROVEEDOR);
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_ID_PAIS", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_ID_TIPODOCU", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_DES_DOCU", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("N_ID_ZONAREGISTRAL", Integer.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_NRO_PARTIDA", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("D_FEC_INGRESO", Date.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_ID_TIPOCONDICION", Integer.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.setParameter(P_C_DES_RUC, dtsIdentificacion.getRuc().toString());
        spQuery.setParameter("C_ID_PAIS", dtsIdentificacion.getPaisId().toString());
        spQuery.setParameter("C_ID_TIPODOCU", dtsIdentificacion.getTipoDocuId());
        spQuery.setParameter("C_DES_DOCU", dtsIdentificacion.getDesDocu());
        spQuery.setParameter("N_ID_ZONAREGISTRAL", dtsIdentificacion.getZonaRegistralId());
        spQuery.setParameter("C_NRO_PARTIDA", dtsIdentificacion.getNroPartida());
        spQuery.setParameter("D_FEC_INGRESO", dtsIdentificacion.getFecIngreso());
        spQuery.setParameter("C_ID_TIPOCONDICION", dtsIdentificacion.getTipoCondicionId());

        return spQuery.getOutputParameterValue(P_RESPUESTA).equals("1");
    }

    @Override
    public List<ProExtNoDom> validarExistenciaCorreoExtNoDom(String correo) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_VALIDAR_CORREO_PROV_EXT_NO_DOM);//Se valida a traves del tamaño del resulset de coincidencias
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_CORREO, String.class, ParameterMode.IN);
        spQuery.setParameter(P_CORREO, correo);
        List<Object[]> lstProveedor = spQuery.getResultList();
        if(!lstProveedor.isEmpty()){
            List<ProExtNoDom> lstCoincidencia = new ArrayList<>(lstProveedor.size());
            lstProveedor.forEach(x->lstCoincidencia.add(new ProExtNoDom(x[0].toString(), x[1].toString(), x[2].toString())));//Indice 0: CodExtNoDom, 1: razonSocial, 2: nomPais
            return lstCoincidencia;
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProExtNoDom> validarExistenciaCorreoRepExtNoDom(String correo) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_VALIDAR_CORREO_REP_EXT_NO_DOM);//Se valida a traves del tamaño del resulset de coincidencias
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_CORREO, String.class, ParameterMode.IN);
        spQuery.setParameter(P_CORREO, correo);
        List<Object[]> lstProveedor = spQuery.getResultList();
        if(!lstProveedor.isEmpty()){
            List<ProExtNoDom> lstCoincidencia = new ArrayList<>(lstProveedor.size());
            lstProveedor.forEach(x->lstCoincidencia.add(new ProExtNoDom(x[0].toString(), x[1].toString(), x[2].toString())));//Indice 0: CodExtNoDom, 1: razonSocial, 2: nomPais
            return lstCoincidencia;
        }
        return Collections.emptyList();
    }

    @Override
    public ProcedureOutputDTO actualizarCorreoExtNoDom(String ruc, String correo) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_REGISTRAR_ACTUALIZAR_CORREO);

        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter(P_C_DES_RUC, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_C_DES_CORREO, String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter(P_MENSAJE, String.class, ParameterMode.OUT);
        spQuery.registerStoredProcedureParameter(P_RESPUESTA, String.class, ParameterMode.OUT);

        spQuery.setParameter(P_C_DES_RUC, ruc);
        spQuery.setParameter(P_C_DES_CORREO, correo);
        spQuery.execute();

        if(spQuery.getOutputParameterValue(P_RESPUESTA).equals("1"))
            return new ProcedureOutputDTO("1","El correo se ha actualizado satisfactoriamente");
        else
            return new ProcedureOutputDTO(
                spQuery.getOutputParameterValue(P_RESPUESTA).toString(),
                spQuery.getOutputParameterValue(P_MENSAJE).toString());
    }

    @Override
    public List<ProExtNoDom> obtenerListadoEmpresasExtNoDom(String paisId, Integer tipoPersonaId, String razonSocial) {
        StoredProcedureQuery spQuery = em.createStoredProcedureQuery(StoredProcedureName.SP_CONSULTAR_PROV_EXT_NO_DOM);//Se valida a traves del tamaño del resulset de coincidencias
        // Registrar los parámetros de entrada y salida
        spQuery.registerStoredProcedureParameter("C_COD_PAIS", String.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("N_IND_PNPJ", Integer.class, ParameterMode.IN);
        spQuery.registerStoredProcedureParameter("C_NOM_RAZSOCIAL", String.class, ParameterMode.IN);
        spQuery.setParameter("C_COD_PAIS", paisId);
        spQuery.setParameter("N_IND_PNPJ", tipoPersonaId);
        spQuery.setParameter("C_NOM_RAZSOCIAL", razonSocial);
        List<Object[]> lstProveedor = spQuery.getResultList();
        if(!lstProveedor.isEmpty()){
            List<ProExtNoDom> lstCoincidencia = new ArrayList<>(lstProveedor.size());
            lstProveedor.forEach(x->lstCoincidencia.add(new ProExtNoDom(x[0].toString(), x[1].toString())));
            return lstCoincidencia;
        }
        return Collections.emptyList();
    }
}
