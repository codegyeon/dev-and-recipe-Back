package com.example.recipe2.recipe.responsedto;

import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.recipe.content.responsedto.ContentResponseDto;

import java.util.ArrayList;
import java.util.List;


public class RecipeResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String subtitle;
    private String ingredient;
    private String url;
    private String tip;
    private String category;
    private List<ContentResponseDto> contentResponseDtoList;

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
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

    public List<ContentResponseDto> getContentResponseDtoList() {
        return contentResponseDtoList;
    }

    public RecipeResponseDto(Recipe recipe) {
        this.id = recipe.getId();
        this.nickname = recipe.getUser().getNickname();
        this.title = recipe.getTitle();
        this.subtitle = recipe.getSubtitle();
        this.ingredient = recipe.getIngredient();
        this.url = recipe.getUrl();
        this.tip = recipe.getTip();
        this.category =recipe.getCategory();
        this.contentResponseDtoList = recipe.getContentList().stream().map(ContentResponseDto::new).toList();
    }
}
