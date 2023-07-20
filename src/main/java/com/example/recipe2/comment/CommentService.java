package com.example.recipe2.comment;

import com.example.recipe2.comment.requestdto.CommentRequestDto;
import com.example.recipe2.comment.responsedto.CommentResponseDto;
import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.recipe.RecipeRepository;
import com.example.recipe2.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;

    public CommentService(CommentRepository commentRepository, RecipeRepository recipeRepository) {
        this.commentRepository = commentRepository;
        this.recipeRepository = recipeRepository;
    }
    //댓글 조회
    public List<CommentResponseDto> getComment(Long recipeId) {
        return commentRepository.findByRecipeId(recipeId).stream().map(CommentResponseDto::new).toList();
    }


    // 댓글생성
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user) {
        Recipe recipe = recipeRepository.findById( Long.parseLong(commentRequestDto.getRecipId())).orElseThrow(
                ()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        Comment comment = new Comment(commentRequestDto, user,recipe);

        return new CommentResponseDto(commentRepository.save(comment));
    }

    // 댓글수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        if(!comment.getUser().getNickname().equals(user.getNickname())){
            throw new IllegalArgumentException("이 댓글은 작성자만 수정할 수 있습니다.");
        }
        comment.updateComment(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    // 댓글삭제
    public void deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        if(!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("이 글은 작성자만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }



    // Recipe recipe = recipeRepository.findById(recipeId)
    // return  recipe.getCommentList().stream().map(CommentResponseDto::new).toList();


}
