package com.example.recipe2.comment.requestdto;

import lombok.Getter;

//@Getter
public class CommentRequestDto {


    private String recipId;

    private String comment;


    public String getRecipId() {
        return recipId;
    }

    public String getComment() {
        return comment;
    }
}
