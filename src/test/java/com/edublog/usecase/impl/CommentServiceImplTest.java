package com.edublog.usecase.impl;

import com.edublog.adapter.CommentMapper;
import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.dto.comment.CommentPostInputDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Comment;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.fixtures.AccountFixture;
import com.edublog.fixtures.ArticleFixture;
import com.edublog.fixtures.CommentFixture;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
import com.edublog.repository.CommentsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    @DisplayName("Given a nonexistent ARTICLE, should throw BusinessException of NOT FOUND type")
    void testCreateNewCommentFailsDueNonexistentArticle() {
        Long articleId = 30L;
        CommentPostInputDto inputDto = new CommentPostInputDto();
        String user = "user";

        given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () ->
                commentService.createNewComment(inputDto, articleId, user)
        );

        assertThat(exception.getClass()).isEqualTo(BusinessException.class);
        assertThat(exception.getHttpStatusCode()).isEqualTo(ExceptionsTemplate.RESOURCE_NOT_FOUND.getHttpStatusCode());
        assertThat(exception.getMessage()).isEqualTo("Article doesn't exist");
        assertThat(exception.getHttpStatus()).isEqualTo(ExceptionsTemplate.RESOURCE_NOT_FOUND.getHttpStatus());
    }

    @Test
    @DisplayName("Given a nonexistent ACCOUNT, should throw BusinessException of NOT FOUND type")
    void testCreateNewCommentFailsDueNonexistentAccount() {
        Long articleId = 30L;
        CommentPostInputDto inputDto = new CommentPostInputDto();
        String user = "user";
        Article foundArticle = ArticleFixture.gimmeValidArticleEntity();

        given(articleRepository.findById(articleId)).willReturn(Optional.of(foundArticle));
        given(accountRepository.findByUsername(user)).willReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () ->
                commentService.createNewComment(inputDto, articleId, user)
        );

        assertThat(exception.getClass()).isEqualTo(BusinessException.class);
        assertThat(exception.getHttpStatusCode()).isEqualTo(ExceptionsTemplate.RESOURCE_NOT_FOUND.getHttpStatusCode());
        assertThat(exception.getMessage()).isEqualTo(ExceptionsTemplate.RESOURCE_NOT_FOUND.getMessage());
        assertThat(exception.getHttpStatus()).isEqualTo(ExceptionsTemplate.RESOURCE_NOT_FOUND.getHttpStatus());
    }
}