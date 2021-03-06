package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.SecurityUserRepository;
import pe.gob.osce.rnp.seg.model.jpa.SecurityPrivilege;
import pe.gob.osce.rnp.seg.model.jpa.SecurityRole;
import pe.gob.osce.rnp.seg.model.jpa.SecurityUser;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityUserServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(SecurityUserServiceImpl.class);

    @Autowired
    private SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        try {
            SecurityUser user = securityUserRepository.findByUsername(username);
            if(user != null) {
                return buildUser(user, buildAuthorities(user.getRoles()));
            }else {
                throw new UsernameNotFoundException("Username doesn't match");
            }

        } catch (Exception e) {
            LOGGER.info("> Excepción | (?): " + username.toUpperCase());
            throw new UsernameNotFoundException("UsernameNotFoundException | (?): " + username.toUpperCase());
        }
    }

    private User buildUser(SecurityUser securityUser, Set<GrantedAuthority> lstRole) {
        return
                new User(securityUser.getUsername(),
                        securityUser.getPassword(),
                        securityUser.isEnabled(),
                        true,
                        true,
                        securityUser.isEnabled(), lstRole);
    }

    private Set<GrantedAuthority> buildAuthorities(Set<SecurityRole> roles) {
        Set<GrantedAuthority> lstRole = new HashSet<>();
        for (SecurityRole role : roles) {
            LOGGER.debug("> USER ROLE: " + role.getRole());
            lstRole.add(new SimpleGrantedAuthority(role.getRole()));
            for (SecurityPrivilege privilege : role.getPrivileges()) {
                lstRole.add(new SimpleGrantedAuthority(privilege.getPrivilege()));
                LOGGER.debug("> USER PRIVILEGE: " + privilege.getPrivilege());
            }
        }
        return lstRole;
    }
}
