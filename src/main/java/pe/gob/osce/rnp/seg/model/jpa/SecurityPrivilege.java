package pe.gob.osce.rnp.seg.model.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
@Table(name = "SECURITY_PRIVILEGE", uniqueConstraints = @UniqueConstraint(columnNames = {"PRIVILEGE", "SECURITY_ROLE_ID"}))
public class SecurityPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SECURITY_PRIVILEGE_ID")
    private int id;

    @Column(name = "PRIVILEGE", nullable = false)
    private String privilege;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SECURITY_ROLE_ID")
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
