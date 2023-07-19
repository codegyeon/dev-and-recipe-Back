package com.example.recipe2.recipe.requestdto;




public class RecipeRequestDto {
    private String title;
    private String subtitle;
    private String ingredient;
    private String tip;
    private String category;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIngredient() {
        return ingredient;
    }


    public String getTip() {
        return tip;
    }

    public String getCategory() {
        return category;
    }

    public RecipeRequestDto() {
    }

    public RecipeRequestDto(String title, String subtitle, String ingredient, String tip, String category) {
        this.title = title;
        this.subtitle = subtitle;
        this.ingredient = ingredient;
        this.tip = tip;
        this.category = category;
    }
}
