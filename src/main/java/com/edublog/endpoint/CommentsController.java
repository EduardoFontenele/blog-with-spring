package com.edublog.endpoint;

import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.dto.comment.CommentPostInputDto;
import com.edublog.usecase.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentService commentService;

    @PostMapping("/create_new/{articleId}")
    public ResponseEntity<CommentGetDto> createNewComment(
            @CurrentSecurityContext(expression = "authentication")Authentication authentication,
            @RequestBody @Validated CommentPostInputDto dto,
            @PathVariable("articleId") Long articleId
            ) {
        return ResponseEntity.ok(commentService.createNewComment(dto, articleId, authentication.getName()));
    }

}
