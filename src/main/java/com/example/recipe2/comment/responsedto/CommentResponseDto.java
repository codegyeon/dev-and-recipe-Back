package com.example.recipe2.comment.responsedto;

import com.example.recipe2.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Getter
//@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.nickname = comment.getUser().getNickname();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }


    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
