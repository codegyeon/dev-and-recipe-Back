package com.example.recipe2.comment.responsedto;

import com.example.recipe2.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Getter
//@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String nickname;
    private LocalDateTime created_At;
    private LocalDateTime modified_At;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.nickname = comment.getUser().getNickname();
        this.created_At = comment.getCreatedAt();
        this.modified_At = comment.getModifiedAt();
    }


    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreated_At() {
        return created_At;
    }
    public LocalDateTime getModified_At() {
        return modified_At;
    }
}
