package pe.gob.osce.rnp.seg.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    private Validador(){}

    public static boolean esNulo(Object objeto) {
        return objeto == null;
    }

    public static boolean validarCorreo(String correo) {
        Pattern pat;
        Matcher mat;
        if(!correo.equals("")) {
            pat = Pattern
                    .compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");

            mat = pat.matcher(correo);

            return mat.find();
        }
        return false;
    }

    public static boolean validarSoloNumeros(String text){
        if(text.contains("[a-zA-Z]+")){
            return false;
        }
        return text.length() == 11;
    }

    public static boolean esCero(Integer numero) {
        return !esNulo(numero) && numero == 0;
    }

    public static boolean esCero(BigDecimal numero) {
        return !esNulo(numero) && numero.compareTo(BigDecimal.ZERO) == 0;
    }

    //Entiendase por usuario como número de ruc o código de proveedor extranjero no domiciliado
    public static boolean validarUsuario(Long ruc){
        if (ruc == null){
            return false;
        }

        String vRuc = String.valueOf(ruc);
        if(!vRuc.startsWith("10") && !vRuc.startsWith("20") && !vRuc.startsWith("9")) {
            return false;
        }

        return vRuc.length() == 11;
    }

    public static boolean validNumberWithLength(Long number, int length){
        if (number == null){
            return false;
        }
        return number.toString().length() == length;
    }



    public static boolean validarClave(String clave) {
        Pattern pat = null;
        Matcher mat = null;

        pat = Pattern.compile("/^(?=.\\d)(?=.[a-z])[0-9a-zA-Z!@#\\$%\\^\\&*\\)\\(+=._-]{8,}$/");

        mat = pat.matcher(clave);

        return mat.find();

    }

}
