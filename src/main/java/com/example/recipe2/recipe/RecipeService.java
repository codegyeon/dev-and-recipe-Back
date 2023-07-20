package com.example.recipe2.recipe;

import com.example.recipe2.recipe.content.Content;
import com.example.recipe2.recipe.content.ContentRepository;
import com.example.recipe2.recipe.requestdto.RecipeRequestDto;
import com.example.recipe2.recipe.responsedto.RecipeListResponseDto;
import com.example.recipe2.recipe.responsedto.RecipeResponseDto;
import com.example.recipe2.s3.S3Upload;
import com.example.recipe2.user.User;
import com.example.recipe2.user.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final S3Upload s3Upload;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    public RecipeService(RecipeRepository recipeRepository, S3Upload s3Upload, UserRepository userRepository, ContentRepository contentRepository) {
        this.recipeRepository = recipeRepository;
        this.s3Upload = s3Upload;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    //전체 게시글 조회
    public List<RecipeListResponseDto> getRecipeList() {
        return recipeRepository.findAll().stream().map(RecipeListResponseDto::new).toList();
    }

    @Transactional
    public RecipeResponseDto createRecipe(User user, RecipeRequestDto recipeRequestDto, MultipartFile file,String content) throws IOException {
        // 파일 업로드

        String url = s3Upload.upload(file);

        Recipe recipe = new Recipe();

        // 배열 형태 content 하나씩 잘라서 저장.
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> strings = objectMapper.readValue(content, new TypeReference<List<String>>() {});

        for (String content1 : strings) {
            Content content2 = new Content(content1);
            recipe.addContentList(content2);
            contentRepository.save(content2);
        }

        recipe.save(recipeRequestDto,url,user);

        recipeRepository.save(recipe);


        return new RecipeResponseDto(recipe);
    }

    //게시글 상세 조회
    public RecipeResponseDto getRecipe(Long recipeId) {
        return new RecipeResponseDto(recipeRepository.findById(recipeId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")));
    }

    // 자기 자신의 게시글 전체 조회
    public List<RecipeResponseDto> getMyRecipeList(User user){

        return user.getRecipeList().stream().map(RecipeResponseDto::new).toList();
    }

    //  게시글 수정
    @Transactional
    public void putRecipe(String nickname, Long recipeId, RecipeRequestDto recipeRequestDto, MultipartFile file, String content) throws IOException {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()-> new IllegalArgumentException("존재하는 게시글이 아닙니다."));

        if (!nickname.equals(recipe.getUser().getNickname())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        String url = s3Upload.upload(file);

        // 레시피의 조리방법 비우기
        recipe.getContentList().clear();

        // 배열 형태 content 하나씩 잘라서 저장.
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> strings = objectMapper.readValue(content, new TypeReference<List<String>>() {});

        // 조리 방법 비운거 다시 채우기
        for (String content1 : strings) {
            Content content2 = new Content(content1);
            recipe.addContentList(content2);
            contentRepository.save(content2);
        }

        recipe.update(recipeRequestDto,url);

    }

    public void deleteRecipe(String nickname, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()-> new IllegalArgumentException("존재하는 게시글이 아닙니다."));
        if (!nickname.equals(recipe.getUser().getNickname())){
            throw  new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        recipeRepository.delete(recipe);

    }
}
