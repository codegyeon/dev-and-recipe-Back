package com.example.recipe2.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Getter
//@Setter
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String method;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String email, String password, String nickname, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public User(String email, String password, String nickname, String method) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.method = method;
        this.role = role;
    }

    public User() {
        
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public String getPassword() {
        return  password;
    }

    public String getEmail() {
        return email;
    }
    public String getNickname() {
        return nickname;
    }
}
