package com.edublog.usecase;

import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePatchDtoInput;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.dto.article.ArticlesWithCommentsGetDto;
import org.springframework.data.domain.Page;

public interface ArticleService {
    ArticlePostDtoOutput createNewArticle(ArticlePostDtoInput publication, String username);
    Page<ArticleGetDto> listAllArticles(String author, Integer pageNumber, Integer pageSize);
    ArticlePatchDtoOutput updateArticle(ArticlePatchDtoInput input, Long id);
    void deleteById(Long id);
    ArticlesWithCommentsGetDto getArticleWithCommentsById(Long id, String author);
}
