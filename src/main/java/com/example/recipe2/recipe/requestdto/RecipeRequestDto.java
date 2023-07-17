package com.example.recipe2.recipe.requestdto;




public class RecipeRequestDto {
    private String foodName;
    private String ingredient;
    private String linkImage;
    private String cookingMethod;

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
