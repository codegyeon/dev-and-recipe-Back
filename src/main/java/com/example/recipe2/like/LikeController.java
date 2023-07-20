package com.example.recipe2.like;


import com.example.recipe2.like.requestdto.LikeRequestDto;
import com.example.recipe2.security.UserDetailsImpl;
import com.example.recipe2.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe/like")
public class LikeController {


    private final LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }



// 어떤 게시글인지? 어떤 유저인지?
    @PostMapping("")
    public ResponseEntity<Like> likeRecipe(@RequestBody LikeRequestDto likeRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.likeRecipe(likeRequestDto,userDetails.getUser());
        return new ResponseEntity("좋아요 처리완료", HttpStatus.OK);
    }

}
