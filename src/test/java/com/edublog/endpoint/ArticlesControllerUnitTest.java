package com.edublog.endpoint;

import com.edublog.config.BlogSecurityConfig;
import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.fixtures.ArticleFixture;
import com.edublog.usecase.ArticleService;
import com.edublog.usecase.Pagination;
import com.edublog.validation.ArticleValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticlesController.class)
@Import(BlogSecurityConfig.class)
public class ArticlesControllerUnitTest {
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

    @Test
    @WithMockUser
    void testListAll() throws Exception {
        //given
        given(articleService.listAllArticles(AUTHOR, 1, 2)).willReturn(mappedArticlesPage);

        mockMvc.perform(get("/api/articles/list_all"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
