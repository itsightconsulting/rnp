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
<<<<<<< HEAD
        Pattern pat = null;
        Matcher mat = null;
        if(correo != "") {
            pat = Pattern
                    .compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");

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
=======
    	if(correo != "") {
	        Pattern pat = null;
	        Matcher mat = null;
	
	        pat = Pattern
	                .compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");
	
	        mat = pat.matcher(correo);
	
	        return mat.find();
    	}
    	return false;
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
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
<<<<<<< HEAD
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
=======
    
    public static boolean validRuc(String ruc){
    	
    	if(ruc.startsWith("10") || ruc.startsWith("20")) {
	        if(ruc!= null && ruc.length() == 11){      	
	            char[] charArray = ruc.toCharArray();
	            for(int i=0; i<charArray.length;i++) {
	                try {
	                    System.out.println(Integer.parseInt(Character.toString(charArray[i])));
	                }catch (NumberFormatException ex){
	                    ex.printStackTrace();
	                    return false;
	                }
	            }
	        return true;    
	    	}
    	}
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
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
        if (clave != "") {
			return true;
		}
        return false;
    }

}