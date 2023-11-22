package com.edublog.fixtures;

import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.model.Article;

import java.time.LocalDateTime;

public class ArticleFixture {

    public static Article gimmeValidArticleEntity() {
        return Article.builder()
                .id(1L)
                .title("Valid Title")
                .body("Valid body")
                .account(AccountFixture.gimmeValidAccountFixture())
                .createdAt(LocalDateTime.parse("2023-11-09T15:30:00"))
                .updatedAt(LocalDateTime.parse("2023-11-09T15:30:00"))
                .build();
    }

    public static ArticlePostDtoInput gimmeValidArticlePostDtoInput() {
        return ArticlePostDtoInput.builder()
                .title("Valid title")
                .body("Valid body")
                .build();
    }

    public static ArticlePostDtoOutput gimmeValidArticlePostDtoOutput() {
        return ArticlePostDtoOutput.builder()
                .title("Valid title")
                .body("Valid body")
                .author("validUser")
                .createdAt("11/09/2023")
                .updatedAt("11/09/2023")
                .build();
    }
}
