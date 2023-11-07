package com.edublog.usecase;

import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.dto.comment.CommentPostInputDto;

public interface CommentService {
    CommentGetDto createNewComment(CommentPostInputDto dto, Long articleId, String username);
}
