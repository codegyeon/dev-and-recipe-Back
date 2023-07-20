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
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


    public Comment(CommentRequestDto commentRequestDto, User user, Recipe recipe) {
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.recipe = recipe;
    }


    public void updateComment(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }


    public Comment() {
    }
    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }
    public Recipe getRecipe() {
        return recipe;
    }

}
