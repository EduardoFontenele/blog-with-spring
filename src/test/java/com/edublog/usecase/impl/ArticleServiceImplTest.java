package com.edublog.usecase.impl;

import com.edublog.adapter.ArticleMapper;
import com.edublog.adapter.CommentMapper;
import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePatchDtoInput;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.dto.article.ArticlesWithCommentsGetDto;
import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Comment;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.fixtures.AccountFixture;
import com.edublog.fixtures.ArticleFixture;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
import com.edublog.repository.CommentsRepository;
import com.edublog.usecase.ArticleService;
import com.edublog.usecase.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ArticleServiceImplTest {
    @Mock
    ArticleRepository articleRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    CommentsRepository commentsRepository;
    @Mock
    ArticleMapper articleMapper;
    @Mock
    CommentMapper commentMapper;
    @InjectMocks
    ArticleServiceImpl articleService;

    @Test
    @DisplayName("Given an ArticlePostDtoInput and the username, should create a new article")
    void testCreateNewArticle() {
        //Given
        ArticlePostDtoInput mockInput = ArticleFixture.gimmeValidArticlePostDtoInput();
        ArticlePostDtoOutput mockOutput;
        String username = "user";
        Account mockAccount = AccountFixture.gimmeValidAccountFixture();
        Article mockArticle = ArticleFixture.gimmeValidArticleEntity();
        Article mockMapedArticle = ArticleFixture.gimmeValidArticleEntity();

        given(accountRepository.findByUsername(username)).willReturn(Optional.of(mockAccount));
        given(articleMapper.toEntity(mockInput, mockAccount)).willReturn(mockMapedArticle);
        given(articleRepository.save(mockArticle)).willReturn(mockMapedArticle);


        //When
        mockOutput = articleService.createNewArticle(mockInput, username);

        //Then
        assertThat(mockOutput).isNotNull();
        verify(accountRepository, times(1)).findByUsername(username);
    }

    @Test
    @DisplayName("Given an account that doesn't exist, should throw a BusinessException")
    void testCreateNewArticleBusinessException() {
        //Given
        String username = "user";
        ArticlePostDtoInput input = new ArticlePostDtoInput();

        given(accountRepository.findByUsername(username)).willReturn(Optional.empty());

        //When
        BusinessException exception = assertThrows(BusinessException.class, () -> {
           articleService.createNewArticle(input, username);
        });

        //Then
        assertThat(exception.getHttpStatus()).isEqualTo(ExceptionsTemplate.BAD_REQUEST.getHttpStatus());
        assertThat(exception.getMessage()).isEqualTo(ExceptionsTemplate.BAD_REQUEST.getMessage());
        assertThat(exception.getHttpStatusCode()).isEqualTo(400);
    }

    @Test
    @DisplayName("Given a valid page request, should return a page of articles")
    void testListAllArticlesWithParameters() {
        //given
        String author = "author";
        int pageNumber = 1;
        int pageSize = 2;
        PageRequest pageRequest = Pagination.buildPageRequest(pageNumber, pageSize);
        List<Article> articleList = new ArrayList<>();
        articleList.add(new Article("Title 1", "Body 1", new Account()));
        articleList.add(new Article("Title 2", "Body 2", new Account()));
        Page<Article> articlePage = new PageImpl<>(articleList, pageRequest, articleList.size());

        given(articleRepository.findAll(pageRequest)).willReturn(articlePage);

        //when
        Page<ArticleGetDto> result = articleService.listAllArticles(author, pageNumber, pageSize);

        //then
        verify(articleRepository, times(1)).findAll(pageRequest);
        assertThat(result).isNotNull();
        assertThat(result.getContent().size()).isEqualTo(pageSize);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Title 1");
        assertThat(result.getContent().get(0).getBody()).isEqualTo("Body 1");
    }

    @Test
    @DisplayName("Given a valid input with title and body, and an id with existent resource, should update the found Article")
    void testUpdateArticleWithValidDataSucceeds() {
        //given
        ArticlePatchDtoInput input = new ArticlePatchDtoInput("New title", "New body");
        Long id = 1L;
        Article foundArticle = new Article("Old title", "Old body", new Account());
        given(articleRepository.findById(id)).willReturn(Optional.of(foundArticle));

        //when
        ArticlePatchDtoOutput result = articleService.updateArticle(input, id);

        //then
        verify(articleRepository, times(1)).findById(id);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("New title");
        assertThat(result.getBody()).isEqualTo("New body");
    }

    @Test
    @DisplayName("Given an invalid input, should throw BusinessException")
    void testUpdateArticleWithInvalidData() {
        //given
        ArticlePatchDtoInput input = new ArticlePatchDtoInput(null, null);
        Long id = 1L;

        //then
        assertThrows(BusinessException.class, () -> {
            articleService.updateArticle(input, id);
        });
    }

    @Test
    @DisplayName("Given a valid input with only the title, and an id with existent resource, should update the found Article")
    void testUpdateArticleWithOnlyTitleSucceeds() {
        //given
        ArticlePatchDtoInput input = new ArticlePatchDtoInput("New title", null);
        Long id = 1L;
        Article foundArticle = new Article("Old title", "Old body", new Account());
        given(articleRepository.findById(id)).willReturn(Optional.of(foundArticle));

        //when
        ArticlePatchDtoOutput result = articleService.updateArticle(input, id);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(input.getTitle());
        assertThat(result.getBody()).isEqualTo(foundArticle.getBody());
    }

    @Test
    @DisplayName("Given a valid input with only the title, and an id with existent resource, should update the found Article")
    void testUpdateArticleWithOnlyBodySucceeds() {
        //given
        ArticlePatchDtoInput input = new ArticlePatchDtoInput(null, "New Body");
        Long id = 1L;
        Article foundArticle = new Article("Old title", "Old body", new Account());
        given(articleRepository.findById(id)).willReturn(Optional.of(foundArticle));

        //when
        ArticlePatchDtoOutput result = articleService.updateArticle(input, id);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(foundArticle.getTitle());
        assertThat(result.getBody()).isEqualTo(input.getBody());
    }

    @Test
    @DisplayName("Should delete an existent article")
    void testDeleteArticle() {
        //when
        articleService.deleteById(1L);

        //then
        verify(articleRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should return an Article DTO with a list of comments")
    void testGetArticleWithCommentsByIdSucceeds() {
        //given
        Long id = 1L;
        String author = "author";
        List<Comment> commentsEntityList = List.of(new Comment("A comment", author, new Account(), new Article()));
        Article article = new Article();

        given(commentsRepository.findAll()).willReturn(commentsEntityList);
        given(articleRepository.findById(id)).willReturn(Optional.of(article));

        //when
        ArticlesWithCommentsGetDto result = articleService.getArticleWithCommentsById(id, author);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getComments().size()).isEqualTo(1);
        assertThat(result.getComments().get(0).getComment()).isEqualTo("A comment");
        assertThat(result.getComments().get(0).getAuthor()).isEqualTo(author);
    }
}