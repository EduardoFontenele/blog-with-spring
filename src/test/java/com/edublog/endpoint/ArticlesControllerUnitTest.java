package com.edublog.endpoint;

import com.edublog.config.BlogSecurityConfig;
import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.fixtures.ArticleFixture;
import com.edublog.usecase.ArticleService;
import com.edublog.usecase.Pagination;
import com.edublog.usecase.impl.ArticleServiceImpl;
import com.edublog.validation.ArticleValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ArticlesController.class)
@Import(BlogSecurityConfig.class)
public class ArticlesControllerUnitTest {
    @MockBean
    private ArticleService articleService;
    @MockBean
    private ArticleValidator articleValidator;
    @Autowired
    private MockMvc mockMvc;
    private final String AUTHOR = "eduardo.fontenele";
    private final String LIST_ALL_PATH = "/api/articles/list_all";

    @AfterEach
    void tearDown() {
        reset(articleService);
    }

    @Test
    @WithMockUser
    @DisplayName("Should return 200 and a page of articles GET dtos")
    void testListAll() throws Exception {
        Page<ArticleGetDto> content = new PageImpl<>(
                ArticleFixture.gimmeListOfValidArticleGetDtos(),
                PageRequest.of(0, 2),
                2);
        //given
        Mockito.when(articleService.listAllArticles(AUTHOR, 1, 2)).thenReturn(content);

        mockMvc.perform(get(LIST_ALL_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

}
