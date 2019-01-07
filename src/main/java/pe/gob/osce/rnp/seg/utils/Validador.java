package pe.gob.osce.rnp.seg.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.hql.internal.ast.tree.IsNullLogicOperatorNode;

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
    	if(correo != "") {
	        Pattern pat = null;
	        Matcher mat = null;
	
	        pat = Pattern
	                .compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");
	
	        mat = pat.matcher(correo);
	
	        return mat.find();
    	}
    	return false;
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
