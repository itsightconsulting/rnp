package pe.gob.osce.rnp.seg.constants;

public class StoredProcedureName {

    private StoredProcedureName(){ }

    public static final String SP_EXISTE_PROVEEDOR =                "spvalidaproveedorrnp";
    public static final String SP_VALIDAR_DATOS_IDEN_PROVEEDOR =    "spvalidardatosidentificacion";
    public static final String SP_OBTENER_FORANEAS_PROVEEDOR =      "spobtenerdatosidentif";
    public static final String SP_OBTENER_OPCS_P_CAMBIO_PASS =      "spobteneropciones";
    public static final String SP_OBTENER_CORREO_PROVEEDOR =        "spobtenercorreousuario";
    public static final String SP_OBTENER_CORREO_REPRESENTANTE =    "spobtenercorreorepresentante";
    public static final String SP_VALIDAR_CORREO_PROV_EXT_NO_DOM =  "spvalidarcorreoextnodom";
    public static final String SP_VALIDAR_CORREO_REP_EXT_NO_DOM =   "spvalidarcorreorepextnodom";
    public static final String SP_CONSULTAR_PROV_EXT_NO_DOM =       "spvalidarempresaextnodom";
    public static final String SP_AUTENTICAR_PROVEEDOR =            "spvalidausuario";

    public static final String SP_REGISTRAR_AUD_ACT_CORREO =        "spregistrarauditcorreo";
    public static final String SP_REGISTRAR_ACTUALIZAR_CORREO =     "spregistrarcorreo";

    public static final String SP_REGISTRAR_COD_VERIFICACION =      "spregistrarcodverificacion";
    public static final String SP_VALIDAR_COD_VERIFICACION =        "spvalidarcodverificacion";

    public static final String SP_OBTENER_BODY_CORREO =             "spobtenermensaje";
    public static final String SP_REGISTRAR_CORREO_ENVIADO =        "spregistrarmensaje";

    public static final String SP_VALIDAR_NUEVA_CLAVE =             "spvalidanuevaclave";
    public static final String SP_REGISTRAR_NUEVA_CLAVE =           "spguardarclaveconvalidacion";
}
