package com.example.recipe2.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByRecipeId(Long recipeId);

    //해당 유저가 해당 레시피에 좋아요를 눌렀는지 안눌렀는지 체크.
    Optional<Like>  findByUserIdAndRecipeId(Long recipeId, Long userId);

    // 유저입장 -- 내가 좋아요를 누른 레시피 목록
    // 레시피 하나당 하나의 좋아요 누를 수 있음.
    // 1번 유저가 1번레시피를 좋아요 --- > 1개
    // 1번 유저가 누른 레시피를 전체를 조회

    // 레시피 입장 -- 누가 좋아요를 눌렀는 좋아요 개수
    // 레시피를 id 를 가지고 User 를 조회 --> user 가 몇명



}
