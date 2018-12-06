package com.itsight.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"Privilege", "SecurityRoleId"}))
public class SecurityPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SecurityPrivilegeId")
    private int id;

    @Column(name = "Privilege", nullable = false)
    private String privilege;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SecurityRoleId")
    private SecurityRole securityRole;

    public SecurityPrivilege() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public SecurityRole getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(SecurityRole securityRole) {
        this.securityRole = securityRole;
    }

}
