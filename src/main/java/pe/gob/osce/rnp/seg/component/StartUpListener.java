package pe.gob.osce.rnp.seg.component;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import pe.gob.osce.rnp.seg.dao.*;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.SecurityPrivilege;
import pe.gob.osce.rnp.seg.model.jpa.SecurityRole;
import pe.gob.osce.rnp.seg.model.jpa.SecurityUser;
import pe.gob.osce.rnp.seg.model.jpa.oauth.OauthClientDetails;
import pe.gob.osce.rnp.seg.svc.CoPaisService;
import pe.gob.osce.rnp.seg.utils.Utilitarios;

@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SecurityUserRepository userRepository;

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Autowired
    private ServletContext context;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CoPaisRepository coPaisRepository;

    @Autowired
    private ProcedureInvokerRepository procedureInvokerRepository;
    
    @Value("${main.repository}")
    private String mainRoute;

    private final Long currentVersion = new Date().getTime();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addingDemoCard();
        addingToContextSession();
        addingInitUsers();
        creatingFileDirectories();
//        actualizarPais();
        registroPais();
//        eliminar();
//        buscar();   
//        sumar();
    }

    public void addingDemoCard(){
        /*if (cardService.findOne(1) == null) {
            cardService.save(new Card("Demo"));
        }*/
    }

    public void sumar() {
    	
    	int resultado;
    	try {
			
    		resultado = cardRepository.getSumaDemo(2, 3);
        	System.out.println("RESPUESTA ES: "+resultado);
    		
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
    	
    }
    
    public void addingToContextSession() {
        context.setAttribute("version", currentVersion);
    }
    
    @Autowired
    private CoPaisService coPaisService;
    
    public void registroPais() {
   
    	try {
    		System.out.println("__INICIA__");
            procedureInvokerRepository.ejecutarSPDemo();
        	//coPaisService.registrar("Belgica");
        	
		} catch (Exception e) {
			System.out.println("__ERROR__");
			e.printStackTrace();
		}
    	 
    }
    
    @Autowired
    private ClaveAccesoRepository claveAccesoRepository;
    
    private void updateUsuario() {
    	try {
 
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void actualizarPais() {
    	   
    	try {
		
    		
    		System.out.println("__INICIA__");
    		
        	coPaisRepository.updatePais(14, "Japon");
        	
		} catch (Exception e) {
			System.out.println("__ERROR__");
			e.printStackTrace();
		}
    	
    }

    
//    public CoPais buscar() {
////    	CoPais co = new CoPais();
//    	try {
//			System.out.println("LISTAR");
//    		CoPais co = coPaisService.buscar(2);
//    		System.out.println("DATO"+co.getDescripcion());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//    	return null;
//    }
    
    public void eliminar() {
		try {
			System.out.println("ELIMINAR");
			coPaisService.eliminar(15);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void addingInitUsers() {
        SecurityUser securityUser = userRepository.findByUsername("rnp_admin");
        if (securityUser == null) {
            SecurityUser secUser = new SecurityUser();
            secUser.setUsername("rnp_admin");
            secUser.setPassword(new BCryptPasswordEncoder().encode("@dmin@2018"));
            secUser.setEnabled(true);

            //Roles
            SecurityRole sr = new SecurityRole();
            sr.setRole("ROLE_SUPER_ADMIN");

            //Privileges
            SecurityPrivilege sp = new SecurityPrivilege();
            sp.setPrivilege("READ_PRIVILEGE");
            sp.setSecurityRole(sr);
            SecurityPrivilege sp1 = new SecurityPrivilege();
            sp1.setPrivilege("WRITE_PRIVILEGE");
            sp1.setSecurityRole(sr);
            //Set Privileges
            Set<SecurityPrivilege> listSp = new HashSet<>();
            listSp.add(sp);
            listSp.add(sp1);
            //Adding to Role father
            sr.setPrivileges(listSp);
            sr.setSecurityUser(secUser);
            //Set Roles(Only 1)
            Set<SecurityRole> listSr = new HashSet<>();
            listSr.add(sr);
            //Adding to User
            secUser.setRoles(listSr);
            userRepository.save(secUser);
        }else
            System.out.println("> Record already exist <");

        //Init application's client
        Optional<OauthClientDetails> outhCliVal =  oauthClientDetailsRepository.findById("rnp_mobile");
        if(!outhCliVal.isPresent()){
            OauthClientDetails oauthCli = new OauthClientDetails();
            oauthCli.setClientId("rnp_mobile");
            oauthCli.setResourceIds("rnp_api");
            oauthCli.setClientSecret(new BCryptPasswordEncoder().encode("user"));
            oauthCli.setScope("read,write");
            oauthCli.setAuthorizedGrantTypes("client_credentials");
            oauthCli.setWebServerRedirectUri("http://127.0.0.1");
            oauthCli.setAuthorities("ROLE_RNP_ADMIN");
            oauthCli.setAccessTokenValidity(600);
            oauthCli.setRefreshTokenValidity(0);
            //https://stackoverflow.com/questions/43676734/spring-oauth2-cant-get-additional-information-from-clientdetailsservice
            oauthCli.setAdditionalInformation("{\"Info\":\"Rnp Api\"}");
            oauthCli.setAutoapprove("true");
            oauthClientDetailsRepository.save(oauthCli);
        }else
            System.out.println("INIT APPLICATION'S CLIENT ALREADY EXISTS");


    }

    public void creatingFileDirectories() {
        String[] childPaths = {};
        Utilitarios.createDirectoryStartUp(mainRoute, childPaths);
    }
}
