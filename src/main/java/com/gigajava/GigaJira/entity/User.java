package com.gigajava.GigaJira.entity;
import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Long domainId;

    public String getUsername() { return username; }
    public String getPassword() {return  password; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public Long getDomainId() { return domainId; }
}