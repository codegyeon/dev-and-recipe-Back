package com.example.recipe2.like;

import com.example.recipe2.like.requestdto.LikeRequestDto;
import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.recipe.RecipeRepository;
import com.example.recipe2.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final RecipeRepository recipeRepository;


    public LikeService(LikeRepository likeRepository, RecipeRepository recipeRepository){
        this.likeRepository = likeRepository;
        this.recipeRepository = recipeRepository;
    }
    public void likeRecipe(LikeRequestDto likeRequestDto,User user){
    Optional<Like> like = likeRepository.findByUserIdAndRecipeId(likeRequestDto.getRecipeId(), user.getId());

        if(!user.getId().equals(likeRequestDto.getRecipeId())) {
            throw new IllegalArgumentException("변경할 수 없습니다.");
        }

        if(like.isPresent()){
            likeRepository.delete(like.get());
        } else{
            Like newLike = new Like(user, recipeRepository.findById(likeRequestDto.getRecipeId()));
            likeRepository.save(newLike);
        }




//        Optional<Like> like
//        Recipe recipe = recipeRepository.findById(likeRequestDto.getRecipeId()).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
//
//       Like like = likeRepository.save(new Like(recipe,user));
//        Optional<Like> like1 = likeRepository.findByUserIdAndRecipeId(likeRequestDto.getRecipeId(), user.getId());
//        like1.isPresent()

    }
}