package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;
    private String ingredient;

    @Column(length = 150)
    private String fileName;

    @Column(length = 300)
    private String filePath;
    private String cookingMethod;


    public Recipe(RecipeRequestDto recipeRequestDto) {
        this.foodName = recipeRequestDto.getFoodName();
        this.ingredient = recipeRequestDto.getIngredient();
        this.linkImage = recipeRequestDto.getLinkImage();
        this.cookingMethod = recipeRequestDto.getCookingMethod();
    }

    public Recipe() {

    }

    public Long getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }
}
