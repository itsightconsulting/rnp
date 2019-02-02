package pe.gob.osce.rnp.seg.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    public static boolean esListaVacia(List<?> lista) {
        if (lista == null || lista.size() == 0)
            return true;
        return false;
    }

    public static boolean esNulo(Object objeto) {
        return objeto == null ? true : false;
    }

    public static boolean esNuloVacio(String variable) {
        if (variable == null || variable.length() == 0)
            return true;

        return false;
    }

    public static boolean estaVacio(String valor) {
        if (valor != null)
            if (!valor.trim().equals(""))
                return false;

        return true;
    }

    public static boolean validarCorreo(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        if(correo != "") {
            pat = Pattern
                    .compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");

            mat = pat.matcher(correo);

            return mat.find();
        }
        return false;
    }

    public static boolean validarSoloNumeros(String text){
        if (text.contains("[a-zA-Z]+") == false && text.length() == 11){
            return true;
        }else{
            return false;
        }
    }

    public static boolean esCero(Integer numero) {
        if (esNulo(numero))
            return false;

        if (numero == 0)
            return true;

        return false;
    }

    public static boolean esCero(BigDecimal numero) {
        if (esNulo(numero))
            return false;

        if (numero.compareTo(BigDecimal.ZERO) == 0)
            return true;

        return false;
    }
    //Entiendase por usuario como número de ruc o código de proveedor extranjero no domiciliado
    public static boolean validarUsuario(Long ruc){
        if (ruc != null){
            String vRuc = String.valueOf(ruc);
            if(vRuc.startsWith("10") || vRuc.startsWith("20") || vRuc.startsWith("9")) {
                if (vRuc.length() == 11) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validNumberWithLength(Long number, int length){
        if (number != null){
            if(number.toString().length() == length)
                return true;
        }
        return false;
    }



    public static boolean validarClave(String clave) {
        Pattern pat = null;
        Matcher mat = null;

        pat = Pattern.compile("/^(?=.\\d)(?=.[a-z])[0-9a-zA-Z!@#\\$%\\^\\&*\\)\\(+=._-]{8,}$/");

        mat = pat.matcher(clave);

        return mat.find();

    }

}