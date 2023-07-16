package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import com.example.recipe2.recipe.responsedto.RecipeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeResponseDto> getRecipeList() {
        return recipeRepository.findAll().stream().map(RecipeResponseDto::new).toList();
    }

    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = recipeRepository.save(new Recipe(recipeRequestDto));
        return new RecipeResponseDto(recipe);
    }
}
