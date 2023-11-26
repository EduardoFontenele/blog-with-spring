package com.edublog.endpoint;


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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ArticlesControllerIntegrationTest {

    @MockBean
    ArticleService articleService;
    @MockBean
    ArticleValidator articleValidator;
    @Autowired
    MockMvc mockMvc;

    PageRequest pageRequest;
    List<ArticleGetDto> articlesDtos;
    Page<ArticleGetDto> mappedArticlesPage;
    private final String AUTHOR = "eduardo.fontenele";
    private final String PASSWORD = "admin123";
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
        given(articleService.listAllArticles(AUTHOR, 1, 2)).willReturn(mappedArticlesPage);

        //then
        MvcResult result = mockMvc.perform(get("/api/articles/list_all").accept(MediaType.APPLICATION_JSON)
                        .with(httpBasic(AUTHOR, PASSWORD)))
                .andExpect(status().isOk())
                .andReturn();
    }
}