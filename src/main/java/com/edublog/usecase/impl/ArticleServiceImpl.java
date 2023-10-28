package com.edublog.usecase.impl;

import com.edublog.domain.dto.article.ArticlePatchDtoInput;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.adapter.ArticleMapper;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicReference;

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

    @Override
    @Transactional
    public ArticlePatchDtoOutput updateArticle(ArticlePatchDtoInput input, Long id) {
        AtomicReference<Article> article = new AtomicReference<>();

        if(!StringUtils.hasText(input.getTitle()) && !StringUtils.hasText(input.getBody()))
            throw new BusinessException(ExceptionsTemplate.BAD_REQUEST);

        articleRepository.findById(id).ifPresent(foundArticle -> {
            if(StringUtils.hasText(input.getTitle())) foundArticle.setTitle(input.getTitle());
            if(StringUtils.hasText(input.getBody())) foundArticle.setBody(input.getBody());
            
            article.set(foundArticle);
            articleRepository.save(foundArticle);
        });

        System.out.println(article.get().toString());

        return articleMapper.toPatchOutputDto(article.get());
    }

}
