package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pe.gob.osce.rnp.seg.constants.ViewConstant;
import pe.gob.osce.rnp.seg.dao.OauthApprovalsRepository;
import pe.gob.osce.rnp.seg.dao.OauthClientDetailsRepository;

import java.util.Map;

import static java.util.Arrays.asList;

@Controller
public class AuthController {

    @Autowired
    private ApprovalStore approvalStore;

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Autowired
    private OauthApprovalsRepository oauthApprovalsRepository;

    @RequestMapping("/")
    public ModelAndView root(Map<String,Object> model){
        model.put("approvals", oauthApprovalsRepository.findAll());
        model.put("clientDetails",oauthClientDetailsRepository.findAll());
        return new ModelAndView ("index",model);
    }

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value="/approval/revoke",method= RequestMethod.POST)
    public String revokApproval(@ModelAttribute Approval approval){

        approvalStore.revokeApprovals(asList(approval));
        tokenStore.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId())
                .forEach(tokenStore::removeAccessToken) ;
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            Model model
    ) {
        if (error != null) {
            if (error.equals("session-expired")) {
                model.addAttribute("expired", "expired");
            } else {
                model.addAttribute("error", "error");
            }
        }
        return ViewConstant.LOGIN;
    }

    @GetMapping(value = "/accesoDenegado", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String permisosInsuficientes() {
        return ViewConstant.ERROR403;
    }

    @GetMapping(value = "/session-expirada", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String expiredSession() {
        return "{\"mensaje\": \"Su sessión ha expirado, por favor dirigirse a la pagina /login\"}";
    }

    @GetMapping(value = "/session-multiple")
    public String expiredBySessionMultiple() {
        return "lock";
    }
}
