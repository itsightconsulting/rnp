package pe.gob.osce.rnp.seg.constants;

public class ViewConstant {

    //MAIN Views
    public static final String LOGIN = "login";
    public static final String ERROR403 = "error/403";
    public static final String MAIN_PARAMETRO = "parametro_main";
    public static final String MAIN_PARAMETRO_FORM = "parametro_form";
    public static final String REDIRECT_MAIN_PARAMETRO = "redirect:/gestion/parametro";
    public static final String REDIRECT_MAIN_PARAMETRO_FORM = "redirect:/gestion/parametro/edicion/";


    //Redirect through http requests
    public static final String REDIRECT_FORM_FALLO_VISA = "redirect:/visa/status";
    public static final String REDIRECT_FORM_CONFIRMATION_VISA = "redirect:/visa/consolidado";
    public static final String REDIRECT_INDEX = "redirect:/inicio";

    public static final int ACTIVO = 1;
    public static final int INACTIVO = 0;

}

