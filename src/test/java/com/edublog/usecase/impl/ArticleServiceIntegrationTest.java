package com.edublog.usecase.impl;

import com.edublog.adapter.ArticleMapper;
import com.edublog.adapter.CommentMapper;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.fixtures.AccountFixture;
import com.edublog.fixtures.ArticleFixture;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
import com.edublog.repository.CommentsRepository;
import com.edublog.usecase.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceIntegrationTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CommentsRepository commentsRepository;
    public final ArticleMapper articleMapper = ArticleMapper.INSTANCE;
    public final CommentMapper commentMapper = CommentMapper.INSTANCE;


    @Test
    @DisplayName("Given a valid input and existent user, should create a new Article")
    @Rollback
    void createNewArticleShouldSucceeds() {
        //arrange
        ArticlePostDtoInput inputDto = ArticleFixture.gimmeValidArticlePostDtoInput();
        String username = AccountFixture.gimmeValidAccountEntityFixture().getUsername();
        Account account = AccountFixture.gimmeValidAccountEntityFixture();
        accountRepository.save(account);
        //act
        ArticlePostDtoOutput result = articleService.createNewArticle(inputDto, username);

        //assert
        assertNotNull(result);
        // Add more assertions based on your specific requirements
    }

    @Test
    void listAllArticles() {
    }

    @Test
    void updateArticle() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getArticleWithCommentsById() {
    }
}