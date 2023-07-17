package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;
    private String ingredient;
    private String linkImage;
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
