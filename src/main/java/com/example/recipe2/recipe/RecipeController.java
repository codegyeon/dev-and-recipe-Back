package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    //  전체 레시피 조회
    @GetMapping("")
    public ResponseEntity getRecipeList(){
        return new ResponseEntity<>(recipeService.getRecipeList(), HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping("")
    public ResponseEntity createRecipe(@RequestBody RecipeRequestDto recipeRequestDto){
        recipeService.createRecipe(recipeRequestDto);
        return new ResponseEntity("success",HttpStatus.OK);
    }

    // 상세 레시피 조회
    @GetMapping("/{recipeId}")
    public ResponseEntity getRecipe(@RequestParam Long recipeId){
        return new ResponseEntity(recipeService.getRecipe(recipeId),HttpStatus.OK);
    }

    @PutMapping("/{recipeId}")



    @DeleteMapping("/{recipeId}")
    public ResponseEntity deleteRecipe(@RequestParam Long recipeId)

}
