package com.example.recipe2.recipe.content;

import com.example.recipe2.recipe.Recipe;
import jakarta.persistence.*;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Recipe recipe;

    public Content() {
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Content(String contents) {
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }
}
