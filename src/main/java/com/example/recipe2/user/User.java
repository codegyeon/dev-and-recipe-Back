package com.example.recipe2.user;

import com.example.recipe2.recipe.Recipe;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipeList = new ArrayList<>();

    public User(String email, String password, String nickname, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
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

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public Long getId() {
        return id;
    }
}
