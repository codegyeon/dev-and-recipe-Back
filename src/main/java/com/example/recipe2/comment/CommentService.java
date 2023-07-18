package com.example.recipe2.comment;

import com.example.recipe2.comment.requestdto.CommentRequestDto;
import com.example.recipe2.comment.responsedto.CommentResponseDto;
import com.example.recipe2.recipe.Recipe;
import com.example.recipe2.recipe.RecipeRepository;
import com.example.recipe2.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;

    public CommentService(CommentRepository commentRepository, RecipeRepository recipeRepository) {
        this.commentRepository = commentRepository;
        this.recipeRepository = recipeRepository;
    }

    // 댓글생성
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user) {
        Recipe recipe = recipeRepository.getById(commentRequestDto.getRecipId());
        Comment comment = new Comment(commentRequestDto, user,recipe);

        return new CommentResponseDto(commentRepository.save(comment));
    }

    // 댓글수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 레시피가 없습니다."));

        if(comment.getUser().getNickname().equals(user.getId())){
            throw new IllegalArgumentException("이 댓글은 작성자만 수정할 수 있습니다.");
        }
        comment.updateComment(commentRequestDto, user);
        return new CommentResponseDto(comment);
    }

    // 댓글삭제
    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        if(!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("이 글은 작성자만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }
}
