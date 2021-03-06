package pe.gob.osce.rnp.seg.utils;

public class Enums {

    public enum Foranea {
        PAIS("P"),
        TIPO_DOCUMENTO("D"),
        ZONA_REGISTRAL("O"),
        TIPO_CONDICION("C");

        final String tp;

        Foranea(String tp){
            this.tp = tp;
        }

        public String get(){ return tp; }
    }

    public enum ResponseCode {
        REGISTRO(-1),
        ACTUALIZACION(-2),
        ELIMINACION(-3),
        EXITO_GENERICA(-4),
        EX_VALIDATION_FAILED(-5),
        EMPTY_RESPONSE(-6),
        SESSION_VALUE_NOT_FOUND(-7),
        SUCCESS_QUERY(-8),
        EX_GENERIC(-9),
        EX_NULL_POINTER(-10),
        EX_JACKSON_INVALID_FORMAT(-11),
        NOT_FOUND_MATCHES(-12),
        EX_SQL_GRAMMAR_EXCEPTION(-13),
        EX_SP_VALIDATION_FAILED(-14),
        EX_MAIL_EXCEPTION(-15),
        EX_SP_VALIDATION_FAILED_BUT_MAIN_REQ_SUCCESS(-16),
        EX_NUMBER_FORMAT(-99),
        EX_MAX_SIZE_MULTIPART(-100),
        EX_MAX_UPLOAD_SIZE(-101),
        EX_ARRAY_INDEX_OUT(-102),
        EX_IO(-103),
        VF_USUARIO_REPETIDO(-150);

        final int code;

        ResponseCode(int code) {
            this.code = code;
        }

        public int get() {
            return code;
        }
    }

}
