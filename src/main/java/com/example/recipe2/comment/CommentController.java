package com.example.recipe2.comment;

import com.example.recipe2.comment.responsedto.CommentResponseDto;
import com.example.recipe2.comment.requestdto.CommentRequestDto;
import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.security.UserDetailsImpl;
import com.example.recipe2.user.ResultResponseEntity;
import com.example.recipe2.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{recipeId}")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
    User user = userDetails.getUser();
    return commentService.createComment(commentRequestDto, user);
    }

    @PutMapping("/{recipeId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return commentService.updateComment(id, commentRequestDto,user);
    }

    @DeleteMapping("/{recipeId}/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        try {
            commentService.deleteComment(id,user);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResultResponseEntity("댓글 삭제 실패"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new ResultResponseEntity("댓글 삭제 성공"), HttpStatus.OK);
    }
}
