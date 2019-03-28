package pe.gob.osce.rnp.seg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.gob.osce.rnp.seg.cfg.AuthorityPropertyEditor;
import pe.gob.osce.rnp.seg.cfg.SplitCollectionEditor;
import pe.gob.osce.rnp.seg.dao.OauthClientDetailsRepository;

import java.util.Collection;
import java.util.Set;

/**
 * Created by ahmed on 21.5.18.
 */

@Controller
@RequestMapping("clients")
public class ClientsController {

    @Autowired
    private JdbcClientDetailsService clientsDetailsService;

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Collection.class,new SplitCollectionEditor(Set.class,","));
        binder.registerCustomEditor(GrantedAuthority.class,new AuthorityPropertyEditor());
    }


    @GetMapping(value="/form")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public String showEditForm(@RequestParam(value="client",required=false)String clientId, Model model){

        ClientDetails clientDetails;
        if(clientId !=null){
            clientDetails=clientsDetailsService.loadClientByClientId(clientId);
        }
        else{
            clientDetails =new BaseClientDetails();
        }

        model.addAttribute("clientDetails",clientDetails);
        return "clients_form";
    }


    @PostMapping(value = "/edit")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public String editClient(
            @ModelAttribute BaseClientDetails clientDetails,
            @RequestParam(value = "newClient", required = false) String newClient
    ) {
        if (newClient == null) {
            clientsDetailsService.updateClientDetails(clientDetails);
        } else {
            clientsDetailsService.addClientDetails(clientDetails);
        }

        if (!clientDetails.getClientSecret().isEmpty()) {
            oauthClientDetailsRepository.updateClientSecret(clientDetails.getClientId(), new BCryptPasswordEncoder().encode(clientDetails.getClientSecret()));
        }
        return "redirect:/";
    }

    @DeleteMapping(value="{client.clientId}/delete")
    public String deleteClient(@ModelAttribute BaseClientDetails clientDetails,@PathVariable("client.clientId") String id){
        clientsDetailsService.removeClientDetails(id);
        return "redirect:/";
    }
}
