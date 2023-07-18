package com.example.recipe2.recipe;

import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import com.example.recipe2.recipe.responsedto.RecipeResponseDto;
import com.example.recipe2.s3.S3Upload;
import com.example.recipe2.user.User;
import com.example.recipe2.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final S3Upload s3Upload;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, S3Upload s3Upload, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.s3Upload = s3Upload;
        this.userRepository = userRepository;
    }

    //전체 게시글 조회
    public List<RecipeResponseDto> getRecipeList() {
        return recipeRepository.findAll().stream().map(RecipeResponseDto::new).toList();
    }

    @Transactional
    public RecipeResponseDto createRecipe(User user, RecipeRequestDto recipeRequestDto, MultipartFile file) throws IOException {

        String url = s3Upload.upload(file);

        Recipe recipe = recipeRepository.save(new Recipe(recipeRequestDto,url,user));
        return new RecipeResponseDto(recipe);
    }

    //게시글 상세 조회
    public RecipeResponseDto getRecipe(Long recipeId) {
        return new RecipeResponseDto(recipeRepository.findById(recipeId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")));
    }

    // 자기 자산의 게시글 전체 조회
    public List<RecipeResponseDto> getMyRecipeList(User user){

        return user.getRecipeList().stream().map(RecipeResponseDto::new).toList();
    }
}
