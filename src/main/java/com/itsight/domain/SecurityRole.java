package com.itsight.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"Role", "SecurityUserId"}))
public class SecurityRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SecurityRoleId")
    private int id;
    @Column(name = "Role", nullable = false)
    private String role;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SecurityUserId", referencedColumnName = "SecurityUserId")
    private SecurityUser securityUser;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "securityRole", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<SecurityPrivilege> privileges;

    public SecurityRole() {
    }

    public SecurityRole(String role, SecurityUser secUser) {
        // TODO Auto-generated constructor stub
        this.role = role;
        this.securityUser = secUser;
    }

    public SecurityRole(String role, int securityUserId) {
        // TODO Auto-generated constructor stub
        this.role = role;
        this.securityUser = new SecurityUser(securityUserId);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    public Set<SecurityPrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<SecurityPrivilege> privileges) {
        this.privileges = privileges;
    }


}
