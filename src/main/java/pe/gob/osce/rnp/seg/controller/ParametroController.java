package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.gob.osce.rnp.seg.constants.ViewConstant;
import pe.gob.osce.rnp.seg.model.jpa.Parametro;
import pe.gob.osce.rnp.seg.svc.ParametroService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/gestion/parametro")
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
public class ParametroController {

    private ParametroService parametroService;

    @Autowired
    public ParametroController(ParametroService parametroService){
        this.parametroService = parametroService;
    }

    @GetMapping(value = "")
    public ModelAndView principal(Model model) {
        model.addAttribute("lstParametro", parametroService.findAll());
        return new ModelAndView(ViewConstant.MAIN_PARAMETRO);
    }
    @GetMapping(value = "/nuevo")
    public ModelAndView redirectAfterRegistro(Model model) {
        model.addAttribute("paramModel", new Parametro());
        return new ModelAndView(ViewConstant.MAIN_PARAMETRO_FORM);
    }

    @GetMapping(value = "/edicion/{id}")
    public ModelAndView redirectAfterRegistro(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("paramModel", parametroService.findOne(id));
        return new ModelAndView(ViewConstant.MAIN_PARAMETRO_FORM);
    }

    @PostMapping(value = "/add")
    public ModelAndView actualizar(@Valid @ModelAttribute(value = "paramModel") Parametro parametro,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if(!bindingResult.hasErrors()){
            if(parametro.getId()>0)
                parametroService.save(parametro);
        }else{
            redirectAttributes.addFlashAttribute("error", "No puede guardar como vacío el valor del parámetro");
            return new ModelAndView(ViewConstant.REDIRECT_MAIN_PARAMETRO_FORM + parametro.getId());
        }

        return new ModelAndView(ViewConstant.REDIRECT_MAIN_PARAMETRO);
    }

    @GetMapping(value = "/cancelar")
    public ModelAndView cancelar() {
        return new ModelAndView(ViewConstant.MAIN_PARAMETRO);
    }



}
