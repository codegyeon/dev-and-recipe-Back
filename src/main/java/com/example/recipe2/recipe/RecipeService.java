package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import com.example.recipe2.recipe.responsedto.RecipeResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto, MultipartFile file) {



        Recipe recipe = recipeRepository.save(new Recipe(recipeRequestDto));
        return new RecipeResponseDto(recipe);
    }

    public RecipeResponseDto getRecipe(Long recipeId) {
        return new RecipeResponseDto(recipeRepository.findById(recipeId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")));
    }
}
