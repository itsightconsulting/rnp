package com.itsight.controller;

import com.itsight.constants.ViewConstant;
import com.itsight.repository.CardRepository;
import com.itsight.repository.OauthApprovalsRepository;
import com.itsight.repository.OauthClientDetailsRepository;
import com.itsight.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Controller
public class AuthController {


    @Autowired
    private ApprovalStore approvalStore;

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Autowired
    private OauthApprovalsRepository oauthApprovalsRepository;

/*    @Autowired
    private CardRepository cardRepository;*/

    @Autowired
    private CardService cardService;

    @Autowired
    private DataSource dataSource;

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

    //	@PreAuthorize("hasAnyRole({'ADMIN','USER'}) or hasAuthority('READ_PRIVILEGE')")
    @GetMapping(value = {"/bienvenido"})
    public String welcome() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_TRAINER") || authority.getAuthority().equals("ROLE_ADMIN"))
                return ViewConstant.PRINCIPAL;
            if(authority.getAuthority().equals("ROLE_RUNNER") || authority.getAuthority().equals("ROLE_STORE"))
                return ViewConstant.PRINCIPAL;
        }
        return ViewConstant.PRINCIPAL;
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

    /* //-------------- LA FUNCTION DEMO -------------------------------

    CREATE OR REPLACE FUNCTION update_card(p_id INT, p_description VARCHAR(255), OUT retrn_id INT)
      RETURNS INT
    LANGUAGE plpgsql
    AS $$
    BEGIN
      IF p_id IS NULL
      THEN
        INSERT INTO card (card_id, description) VALUES (p_id, p_description);
        retrn_id=p_id;
      ELSE
        UPDATE card
        SET description = p_description
        WHERE card_id = p_id;
        retrn_id=p_id;
      END IF;
    END;
    $$;

     */
    /*@GetMapping(value = "/function/demo/{id}/{descripcion}")//Para ejecutar cualquier peticion http debes loguearte primero
    public @ResponseBody Integer probandoPgFunctionDemo(@PathVariable(name = "id") String id, @PathVariable(name = "descripcion") String descripcion) {
        Integer output = cardRepository.updateWithSp(Integer.parseInt(id), descripcion);
        return output;
    }*/

    @GetMapping(value = "/get/suma/{num1}/{num2}")//Para ejecutar cualquier peticion http debes loguearte primero
    public @ResponseBody Integer getSimpleSumaFromSqlSeverStoredProcedure(@PathVariable(name = "num1") Integer num1, @PathVariable(name = "num2") Integer num2) {
        return cardService.getSimpleSumaSp(num1, num2);
    }
}
