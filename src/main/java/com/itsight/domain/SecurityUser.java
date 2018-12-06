package com.itsight.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Set;

@NamedEntityGraphs({@NamedEntityGraph(name = "securityUser"),
        @NamedEntityGraph(name = "securityUser.roles", attributeNodes = {
                @NamedAttributeNode(value = "roles")
        })
})
@Entity
public class SecurityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SecurityUserId")
    private int id;
    @Column(name = "Username", unique = true, updatable = false)
    private String username;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "Enabled", nullable = false)
    private boolean enabled;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "securityUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<SecurityRole> roles;

    public SecurityUser() {
    }

    public SecurityUser(int id) {
        this.id = id;
    }

    public SecurityUser(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SecurityRole> roles) {
        this.roles = roles;
    }
}
