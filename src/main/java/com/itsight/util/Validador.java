package com.itsight.util;

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

        pat = Pattern
                .compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");

        mat = pat.matcher(correo);

        return mat.find();

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

}