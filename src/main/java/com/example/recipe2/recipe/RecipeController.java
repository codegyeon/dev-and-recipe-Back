package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import com.example.recipe2.security.UserDetailsImpl;
import com.example.recipe2.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    //  전체 레시피 조회
    @GetMapping("")
    public ResponseEntity getRecipeList() {
        logger.error("게시글 전체 조회 시도");
        return new ResponseEntity<>(recipeService.getRecipeList(), HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping("")
    public ResponseEntity createRecipe(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart(name = "recipeRequestDto") RecipeRequestDto recipeRequestDto, @RequestPart(name = "file") MultipartFile file) {
        logger.error("게시글 작성 시도");
        try{
            recipeService.createRecipe(userDetails.getUser(),recipeRequestDto, file);
        }catch (Exception e){
            return new ResponseEntity<>("업로드 실패",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 상세 레시피 조회
    @GetMapping("/{recipeId}")
    public ResponseEntity getRecipe(@RequestParam Long recipeId) {
        logger.error("게시글 상세 조회 시도");
        return new ResponseEntity<>(recipeService.getRecipe(recipeId), HttpStatus.OK);
    }

//    @PutMapping("/{recipeId}")


//    @DeleteMapping("/{recipeId}")
//    public ResponseEntity deleteRecipe(@RequestParam Long recipeId);{
//}
}
