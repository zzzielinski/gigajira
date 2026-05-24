package com.gigajava.GigaJira.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() { return  id; }
    public String getUsername() { return username; }
    public String getPassword() {return  password; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public Long getDomainId() { return domainId; }
}