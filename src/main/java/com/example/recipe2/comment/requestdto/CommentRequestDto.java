package com.example.recipe2.comment.requestdto;

import lombok.Getter;

//@Getter
public class CommentRequestDto {


    private String recipeId;

    private String comment;


    public String getRecipeId() {
        return recipeId;
    }

    public String getComment() {
        return comment;
    }
}
