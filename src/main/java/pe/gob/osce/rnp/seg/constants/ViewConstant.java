package pe.gob.osce.rnp.seg.constants;

public class ViewConstant {

    private ViewConstant(){}

    //MAIN Views
    public static final String LOGIN = "login";
    public static final String ERROR403 = "error/403";
    public static final String MAIN_PARAMETRO = "parametro_main";
    public static final String MAIN_PARAMETRO_FORM = "parametro_form";

    //Redirect through http requests
    public static final String REDIRECT_MAIN_PARAMETRO = "redirect:/gestion/parametro";
    public static final String REDIRECT_MAIN_PARAMETRO_FORM = "redirect:/gestion/parametro/edicion/";

    public static final int ACTIVO = 1;
    public static final int INACTIVO = 0;

}

