package com.example.recipe2.comment;

import com.example.recipe2.comment.responsedto.CommentResponseDto;
import com.example.recipe2.comment.requestdto.CommentRequestDto;
import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.security.UserDetailsImpl;
import com.example.recipe2.user.ResultResponseEntity;
import com.example.recipe2.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글조회 -- > 해당 레시피 id 로 등록된 댓글을 전부 가져옴
    //get 메서드는 바디에 정보가 들어가지 않는게 좋음.
    @GetMapping("/{recipeId}")
    public ResponseEntity<List<Comment>> getComment(@PathVariable Long recipeId){
        logger.error("댓글 조회 시도");
        return new ResponseEntity(commentService.getComment(recipeId), HttpStatus.OK);
    }

    //댓글생성 --> 레시피 id 값을 가지고 조회해서 댓글에 넣음
    @PostMapping("")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        logger.error("댓글 생성 시도");
        logger.error(commentRequestDto.getRecipId().toString());
        logger.error(commentRequestDto.getComment());
        CommentResponseDto commentResponseDto;
        try{
            commentResponseDto = commentService.createComment(commentRequestDto, userDetails.getUser());
        }catch (Exception e){
            return new ResponseEntity(new ResultResponseEntity(e.getMessage()) ,HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(commentResponseDto,HttpStatus.OK);
    }

    //댓글 수정 ---> 해당 레시피랑 댓글 을 조회하여 수정  ex) 1번 레시피의 5번째 댓글 수정
    // url 논의
    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        logger.error("댓글 수정 시도");
        CommentResponseDto commentResponseDto;
        try{
            commentResponseDto = commentService.updateComment(commentId, commentRequestDto,userDetails.getUser());
        }catch (Exception e){
            return  new ResponseEntity(new ResultResponseEntity(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(commentResponseDto,HttpStatus.OK);
    }

    // 댓글 삭제 ----> 해당 레시피랑 댓글을 조회하여 수정
    // url 논의
    @DeleteMapping("{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        logger.error("댓글 삭제 시도");
        try {
            commentService.deleteComment(commentId, userDetails.getUser());
        } catch (Exception e) {
            return new ResponseEntity(new ResultResponseEntity("댓글 삭제 실패"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(new ResultResponseEntity("댓글 삭제 성공"), HttpStatus.OK);
    }
}
