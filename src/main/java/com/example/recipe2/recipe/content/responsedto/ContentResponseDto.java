package com.example.recipe2.recipe.content.responsedto;

import com.example.recipe2.recipe.content.Content;

public class ContentResponseDto {
    private String content;


    public ContentResponseDto() {
    }

    public ContentResponseDto(Content content) {
        this.content = content.getContents();
    }

    public String getContent() {
        return content;
    }
}
