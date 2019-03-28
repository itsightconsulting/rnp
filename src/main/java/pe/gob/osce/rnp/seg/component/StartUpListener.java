package pe.gob.osce.rnp.seg.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pe.gob.osce.rnp.seg.dao.OauthClientDetailsRepository;
import pe.gob.osce.rnp.seg.dao.SecurityUserRepository;
import pe.gob.osce.rnp.seg.model.jpa.SecurityPrivilege;
import pe.gob.osce.rnp.seg.model.jpa.SecurityRole;
import pe.gob.osce.rnp.seg.model.jpa.SecurityUser;
import pe.gob.osce.rnp.seg.model.jpa.oauth.OauthClientDetails;
import pe.gob.osce.rnp.seg.utils.Utilitarios;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Long currentVersion = new Date().getTime();
    private static final String OAUTH_BASE_USERNAME = "rnp_osce";

    public static final Logger LOGGER = LogManager.getLogger(StartUpListener.class);

    @Autowired
    private ServletContext context;

    @Value("${main.repository}")
    private String mainRoute;

    @Autowired
    private SecurityUserRepository userRepository;

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addingToContextSession();
        addingInitUsers();
        creatingFileDirectories();
    }

    public void addingToContextSession() {
        context.setAttribute("version", currentVersion);
    }

    public void addingInitUsers() {
        SecurityUser securityUser = userRepository.findByUsername(OAUTH_BASE_USERNAME);
        if (securityUser == null) {
            SecurityUser secUser = new SecurityUser();
            secUser.setUsername(OAUTH_BASE_USERNAME);
            secUser.setPassword(new BCryptPasswordEncoder().encode("itsight19@13"));
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
            LOGGER.info("> Record already exist <");

        //Init application's client
        Optional<OauthClientDetails> optionalOauthClient =  oauthClientDetailsRepository.findById(OAUTH_BASE_USERNAME);
        if(!optionalOauthClient.isPresent()) {
            OauthClientDetails oauthCli = new OauthClientDetails();
            oauthCli.setClientId(OAUTH_BASE_USERNAME);
            oauthCli.setResourceIds("rnp_api");
            oauthCli.setClientSecret(new BCryptPasswordEncoder().encode("itsight19@13"));
            oauthCli.setScope("read,write");
            oauthCli.setAuthorizedGrantTypes("client_credentials");
            oauthCli.setWebServerRedirectUri("http://127.0.0.1");
            oauthCli.setAuthorities("RNP_ADMIN");
            oauthCli.setAccessTokenValidity(15);//Token sin vencimiento
            oauthCli.setRefreshTokenValidity(30);
            oauthCli.setAdditionalInformation("{\"Info\":\"Rnp Api\"}");
            oauthCli.setAutoapprove("true");
            oauthClientDetailsRepository.save(oauthCli);
        } else
            LOGGER.info("INIT APPLICATION'S CLIENT ALREADY EXISTS");
    }

    public void creatingFileDirectories() {
        String[] childPaths = {};
        Utilitarios.createDirectoryStartUp(mainRoute, childPaths);
    }
}
