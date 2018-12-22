package pe.gob.osce.rnp.seg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osce.rnp.seg.dao.ProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.svc.CoPaisService;

@RestController
@RequestMapping("/pais")
public class CoPaisController {
	
//	@Autowired
//    private ApprovalStore approvalStore;

//    @Autowired
//    private OauthClientDetailsRepository oauthClientDetailsRepository;
//	
//    @Autowired
//    private OauthApprovalsRepository oauthApprovalsRepository;
//
	@Autowired
	private ProcedureInvokerRepository procedureInvokerRepository; 
	
    @Autowired
    private DataSource dataSource;

//    
	@Autowired
	private CoPaisService coPaisService;
//	
//	@Autowired
//	private ProcedureInvoker procedureInvoker;
	
    @RequestMapping("/{nombre}")
    public Mensaje root(@PathVariable(value = "nombre") String nombre){
    	return procedureInvokerRepository.ejecutarSPDemo(nombre);
    }
//	
//    @Autowired
//    private TokenStore tokenStore;
//    
//    @RequestMapping(value="/approval/revoke",method= RequestMethod.POST)
//    public String revokApproval(@ModelAttribute Approval approval){
//
//        approvalStore.revokeApprovals(asList(approval));
//        tokenStore.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId())
//                .forEach(tokenStore::removeAccessToken) ;
//        return "redirect:/";
//    }
//
//    @GetMapping(value = "/login")
//    public String loginForm(@RequestParam(value = "error", required = false) String error,
//                            Model model
//    ) {
//        if (error != null) {
//            if (error.equals("session-expired")) {
//                model.addAttribute("expired", "expired");
//            } else {
//                model.addAttribute("error", "error");
//            }
//        }
//        return ViewConstant.LOGIN;
//    }

    //	@PreAuthorize("hasAnyRole({'ADMIN','USER'}) or hasAuthority('READ_PRIVILEGE')")
//    @GetMapping(value = {"/bienvenido"})
//    public String welcome() {
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        for (GrantedAuthority authority: authorities){
//            if(authority.getAuthority().equals("ROLE_TRAINER") || authority.getAuthority().equals("ROLE_ADMIN"))
//                return ViewConstant.PRINCIPAL;
//            if(authority.getAuthority().equals("ROLE_RUNNER") || authority.getAuthority().equals("ROLE_STORE"))
//                return ViewConstant.PRINCIPAL;
//        }
//        return ViewConstant.PRINCIPAL;
//    }
//
//    @GetMapping(value = "/accesoDenegado", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public String permisosInsuficientes() {
//        return ViewConstant.ERROR403;
//    }
//
//    @GetMapping(value = "/session-expirada", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public @ResponseBody
//    String expiredSession() {
//        return "{\"mensaje\": \"Su sessión ha expirado, por favor dirigirse a la pagina /login\"}";
//    }
//
//    @GetMapping(value = "/session-multiple")
//    public String expiredBySessionMultiple(Model model) {
//        return "lock";
//    }

    
	@GetMapping(value = "/registrar/{descripcion}")
    public @ResponseBody String registrar(@PathVariable(value = "descripcion") String string) {
		System.out.println("CONTROLADOR");
        final SimpleJdbcCall updateEmployeeCall = new SimpleJdbcCall(dataSource).withFunctionName("sp_insertarPais");
        final Map<String, Object> params = new HashMap<>();
//        params.put("p_id", id);
        params.put("description", string);

        final Map<String, Object> result = updateEmployeeCall.execute(params);
        System.out.println(result.get("returnvalue"));
        return "1";//1:Éxito
    	
    }
}
