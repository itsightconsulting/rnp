package pe.gob.osce.rnp.seg.utils;

public class Enums {

    public enum ResidueRanges {
        MINIMO, MAXIMO
    }

    public enum SubPlanType {
        ZERO, PREPAGO, POSTPAGO, FREE
    }

    public enum ResponseCode {
        SUCCESS(1),
        DENIED(0),
        ERROR_GENERAL(-500),
        SUCCESS_UPDATE(-1),

        ERROR_NO_EXISTE(-10),
        ERROR_DESHABILITADO(-11),
        ERROR_SIN_REGISTROS(-12),
        ERROR_FALTAN_DATOS(-13),
        ERROR_DATOS(-14),

        EX_NULL_POINTER(-97),
        EX_JACKSON_INVALID_FORMAT(-98),
        EX_NUMBER_FORMAT(-99),
        EX_MAX_SIZE_MULTIPART(-100),
        EX_MAX_UPLOAD_SIZE(-101);

        final int code;

        ResponseCode(int code) {
            this.code = code;
        }

        public String get() {
            return String.valueOf(code);
        }
    }

    public enum ETransactionType{
        RECHARGE_TYPE("Recarga");


        final String code;

        ETransactionType(String code) {
            this.code = code;
        }

        public String get() {
            return String.valueOf(code);
        }
    }

}
