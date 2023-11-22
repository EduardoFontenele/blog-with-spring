package com.edublog.usecase.impl;

import com.edublog.adapter.CommentMapper;
import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.dto.comment.CommentPostInputDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Comment;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
import com.edublog.repository.CommentsRepository;
import com.edublog.usecase.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsRepository commentsRepository;
    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;
    public final CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Override
    public CommentGetDto createNewComment(CommentPostInputDto dto, Long articleId, String username) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new BusinessException(ExceptionsTemplate.RESOURCE_NOT_FOUND, articleId.toString(), "Article doesn't exist"));
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ExceptionsTemplate.RESOURCE_NOT_FOUND));
        Comment savedComment = commentMapper.toEntity(article, account, username, dto);
        commentsRepository.save(savedComment);
        return commentMapper.toGetDto(savedComment);
    }
}
