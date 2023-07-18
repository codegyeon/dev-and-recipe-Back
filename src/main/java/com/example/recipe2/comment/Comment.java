package com.example.recipe2.comment;


import com.example.recipe2.comment.requestdto.CommentRequestDto;
import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
//@Getter
//@Setter
//@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn
    private Recipe recipe;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public Comment(CommentRequestDto commentRequestDto, User user, Recipe recipe) {
        this.content = commentRequestDto.getContent();
        this.user = user;
        this.recipe = recipe;
    }


    public void updateComment(CommentRequestDto commentRequestDto, User user) {
        this.content = commentRequestDto.getContent();
        this.user = user;
    }


    public Comment() {
    }
    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public User getUser() {
        return user;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

}
