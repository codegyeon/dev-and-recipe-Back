package com.example.recipe2.comment.requestdto;

import lombok.Getter;

//@Getter
public class CommentRequestDto {


    private Long recipId;

    private String comment;


    public Long getRecipId() {
        return recipId;
    }

    public String getComment() {
        return comment;
    }
}
