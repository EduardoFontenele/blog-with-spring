package com.edublog.usecase.impl;

import com.edublog.mapper.ArticleMapper;
import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
import com.edublog.usecase.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.edublog.usecase.Pagination;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;
    public final ArticleMapper articleMapper = ArticleMapper.INSTANCE;

    @Override
    public ArticlePostDtoOutput createNewArticle(ArticlePostDtoInput publication, String username) {
        Account user = accountRepository.findByUsername(username).orElseThrow(() ->
                new BusinessException(ExceptionsTemplate.BAD_REQUEST));
        Article savedPub = articleRepository.save(articleMapper.toEntity(publication, user));
        return articleMapper.toPostOutputDto(savedPub);
    }

    @Override
    public Page<ArticleGetDto> listAllArticles(String author, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = Pagination.buildPageRequest(pageNumber, pageSize);
        return articleRepository.findAll(pageRequest).map(articleMapper::toGetDto);
    }

}
