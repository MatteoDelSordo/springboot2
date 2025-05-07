package it.siinfo.springboot2.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    private String authority;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Users> users;

    public Role () {
    }

    public Role (String authority,
                 Set<Users> users) {
        this.authority = authority;
        this.users = users;
    }

    public Long getId () {
        return idRole;
    }

    public void setId (Long id) {
        this.idRole = id;
    }

    @Override
    public String getAuthority () {
        return authority;
    }

    public void setAuthority (String authority) {
        this.authority = authority;
    }

    public Set<Users> getUsers () {
        return users;
    }

    public void setUsers (Set<Users> users) {
        this.users = users;
    }
}
