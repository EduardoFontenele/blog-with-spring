package com.edublog.fixtures;

import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.model.Article;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ArticleFixture {

    public static Article gimmeValidArticleEntity() {
        return Article.builder()
                .id(1L)
                .title("Valid Title")
                .body("Valid body")
                .account(AccountFixture.gimmeValidAccountEntityFixture())
                .createdAt(LocalDateTime.parse("2023-11-09T15:30:00"))
                .updatedAt(LocalDateTime.parse("2023-11-09T15:30:00"))
                .build();
    }

    public static List<Article> gimmeListOfValidArticleEntities() {
        Article article1 = Article.builder()
                .id(1L)
                .title("Valid Title 1")
                .body("Valid body 1")
                .account(AccountFixture.gimmeValidAccountEntityFixture())
                .createdAt(LocalDateTime.parse("2023-11-09T15:30:00"))
                .updatedAt(LocalDateTime.parse("2023-11-09T15:30:00"))
                .build();

        Article article2 = Article.builder()
                .id(2L)
                .title("Valid Title 2")
                .body("Valid body 2")
                .account(AccountFixture.gimmeValidAccountEntityFixture())
                .createdAt(LocalDateTime.parse("2023-11-09T16:45:00"))
                .updatedAt(LocalDateTime.parse("2023-11-09T16:45:00"))
                .build();

        return Arrays.asList(article1, article2);
    }

    public static ArticlePostDtoInput gimmeValidArticlePostDtoInput() {
        return ArticlePostDtoInput.builder()
                .title("Valid title")
                .body("Valid body")
                .build();
    }

    public static ArticleGetDto gimmeValidArticleGetDto() {
        ArticleGetDto articleGetDto = new ArticleGetDto();
        articleGetDto.setId(1L);
        articleGetDto.setTitle("Valid Title 1");
        articleGetDto.setBody("Valid body 1");
        articleGetDto.setCreatedAt("09/11/2023 15:30");
        articleGetDto.setUpdatedAt("09/11/2023 15:30");
        articleGetDto.setAuthor("eduardo.fontenele");

        return articleGetDto;
    }

    public static List<ArticleGetDto> gimmeListOfValidArticleGetDtos() {
        ArticleGetDto article1 =  ArticleGetDto.builder()
                .id(1L)
                .title("Valid Title 1")
                .body("Valid body 1")
                .createdAt("09/11/2023 15:30")
                .updatedAt("09/11/2023 15:30")
                .author("eduardo.fontenele")
                .build();

        ArticleGetDto article2 = ArticleGetDto.builder()
                .id(2L)
                .title("Valid Title 2")
                .body("Valid body 2")
                .createdAt("09/11/2023 16:45")
                .updatedAt("09/11/2023 16:45")
                .author("eduardo.fontenele")
                .build();

        return Arrays.asList(article1, article2);
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
