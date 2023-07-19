package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import com.example.recipe2.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column
    private String subtitle;

    @Column(length = 300)
    private String ingredient;

    @Column(length = 300)
    private String url;

    @Column(length = 300)
    private String tip;

    @Column
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public Recipe(RecipeRequestDto recipeRequestDto, String url, User user) {
        this.title = recipeRequestDto.getTitle();
        this.subtitle = recipeRequestDto.getSubtitle();
        this.ingredient = recipeRequestDto.getIngredient();
        this.url = url;
        this.tip = recipeRequestDto.getTip();
        this.category = recipeRequestDto.getCategory();
        this.user = user;
    }

    public Recipe() {

    }



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getUrl() {
        return url;
    }

    public String getTip() {
        return tip;
    }

    public String getCategory() {
        return category;
    }

}
