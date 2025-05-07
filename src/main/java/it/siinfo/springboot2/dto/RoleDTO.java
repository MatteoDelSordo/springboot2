package it.siinfo.springboot2.dto;

public class RoleDTO {
    private Long idRole;
    private String authority;

    public RoleDTO () {
    }

    public RoleDTO (String authority) {
        this.authority = authority;
    }

    public Long getId () {
        return idRole;
    }

    public void setId (Long id) {
        this.idRole = id;
    }

    public String getAuthority () {
        return authority;
    }

    public void setAuthority (String authority) {
        this.authority = authority;
    }
}
