package com.edublog.endpoint;


import com.edublog.config.BlogSecurityConfig;
import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.fixtures.ArticleFixture;
import com.edublog.usecase.ArticleService;
import com.edublog.usecase.Pagination;
import com.edublog.validation.ArticleValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticlesController.class)
@Import(BlogSecurityConfig.class)
class ArticlesControllerTest {

    @MockBean
    ArticleService articleService;
    @MockBean
    ArticleValidator articleValidator;
    @Autowired
    WebApplicationContext context;
    @Autowired
    MockMvc mockMvc;

    PageRequest pageRequest;
    List<ArticleGetDto> articlesDtos;
    Page<ArticleGetDto> mappedArticlesPage;
    String author = "eduardo.fontenele";
    @BeforeEach
    void setUp() {
        pageRequest = Pagination.buildPageRequest(1, 2);
        articlesDtos = ArticleFixture.gimmeListOfValidArticleGetDtos();
        mappedArticlesPage = new PageImpl<>(articlesDtos, pageRequest, articlesDtos.size());

    }

    @AfterEach
    void tearDown() {
        reset(articleService);
    }
    
    @Test
    @DisplayName("Should return 200 and a page of articles")
    void testListAllArticles() throws Exception {
        //given
        given(articleService.listAllArticles(author, 1, 2)).willReturn(mappedArticlesPage);

        //then
        MvcResult result = mockMvc.perform(get("/api/articles/list_all").accept(MediaType.APPLICATION_JSON)
                        .with(httpBasic("eduardo.fontenele", "admin123")))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.toString());
    }
}