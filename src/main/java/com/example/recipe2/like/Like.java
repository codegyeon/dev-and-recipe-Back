package com.example.recipe2.like;

import com.example.recipe2.like.requestdto.LikeRequestDto;
import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.user.User;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Like(){}

    public Like(Recipe recipe , User user) {
        this.user = user;
        this.recipe = recipe;
    }


    public Like(User user, Optional<Recipe> byId) {
    }
}
