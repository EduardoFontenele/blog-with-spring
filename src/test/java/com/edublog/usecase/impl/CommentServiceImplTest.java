package com.edublog.usecase.impl;

import com.edublog.adapter.CommentMapper;
import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.dto.comment.CommentPostInputDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Comment;
import com.edublog.fixtures.AccountFixture;
import com.edublog.fixtures.ArticleFixture;
import com.edublog.fixtures.CommentFixture;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
import com.edublog.repository.CommentsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CommentServiceImplTest {
    @Mock
    CommentsRepository commentsRepository;
    @Mock
    ArticleRepository articleRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    CommentMapper commentMapper;
    @InjectMocks
    CommentServiceImpl commentService;

    @Test
    @DisplayName("Given a valid comment input, an existent article and account, should create comment")
    void testCreateNewCommentSucceeds() {
        //given
        Long articleId = ArticleFixture.gimmeValidArticleEntity().getId();
        String username = AccountFixture.gimmeValidAccountFixture().getUsername();
        CommentPostInputDto input = CommentFixture.gimmeValidCommentPostInputDtoFixture();
        Article article = ArticleFixture.gimmeValidArticleEntity();
        Account account = AccountFixture.gimmeValidAccountFixture();
        Comment savedComment = CommentFixture.gimmeValidCommentFixture();

        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));
        given(accountRepository.findByUsername(username)).willReturn(Optional.of(account));
        given(commentsRepository.save(savedComment)).willReturn(savedComment);

        //when
        CommentGetDto result = commentService.createNewComment(input, articleId, username);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getComment()).isEqualTo(input.getComment());
    }
}