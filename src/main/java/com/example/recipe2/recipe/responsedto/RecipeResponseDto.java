package com.example.recipe2.recipe.responsedto;

import com.example.recipe2.recipe.Recipe;
import lombok.Getter;



public class RecipeResponseDto {
    private Long id;

    private String foodName;
    private String ingredient;
    private String linkImage;
    private String cookingMethod;

    public RecipeResponseDto(Recipe recipe) {
        this.id = recipe.getId();
        this.foodName = recipe.getFoodName();
        this.ingredient = recipe.getIngredient();
        this.linkImage = recipe.getLinkImage();
        this.cookingMethod = recipe.getCookingMethod();
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
