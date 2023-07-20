package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import com.example.recipe2.security.UserDetailsImpl;
import com.example.recipe2.user.UserController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

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

    @PostMapping("")
    public ResponseEntity createRecipe
    (
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart(name = "title") String title,
            @RequestPart(name = "subtitle") String subtitle,
            @RequestPart(name = "ingredient") String ingredient,
            @RequestPart(name = "tip") String tip,
            @RequestPart(name = "category") String category,
            @RequestPart(name = "content") String content,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) {
        logger.error(content);
        logger.error("게시글 작성 시도");

        try{
            recipeService.createRecipe(userDetails.getUser(),
                    new RecipeRequestDto(title,subtitle,ingredient, tip,  category),
                    file,
                    content);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 상세 레시피 조회
    @GetMapping("/{recipeId}")
    public ResponseEntity getRecipe(@PathVariable Long recipeId) {
        logger.error("게시글 상세 조회 시도");
        return new ResponseEntity<>(recipeService.getRecipe(recipeId), HttpStatus.OK);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity putRecipe(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long recipeId,
            @RequestPart(name = "title") String title,
            @RequestPart(name = "subtitle") String subtitle,
            @RequestPart(name = "ingredient") String ingredient,
            @RequestPart(name = "tip") String tip,
            @RequestPart(name = "category") String category,
            @RequestPart(name = "content") String content,
            @RequestPart(name = "file", required = false) MultipartFile file
    ){
        logger.error("게시글 수정 시도");
        try {
            recipeService.putRecipe(userDetails.getUser().getNickname(),
                    recipeId,new RecipeRequestDto(title,subtitle,ingredient, tip,  category),
                    file,
                    content
            );
        }catch (Exception e){
            return new ResponseEntity<>("업로드 실패",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity deleteRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long recipeId){
        logger.error("게시글 삭제 시도");
        try{
            recipeService.deleteRecipe(userDetails.getUser().getNickname(),recipeId);
        }catch (Exception e){
            return new ResponseEntity<>("삭제 실패",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);

    }
}

