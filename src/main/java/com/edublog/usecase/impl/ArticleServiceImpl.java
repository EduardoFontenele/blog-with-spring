package com.edublog.usecase.impl;

import com.edublog.adapter.CommentMapper;
import com.edublog.domain.dto.article.ArticlePatchDtoInput;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.adapter.ArticleMapper;
import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.dto.article.ArticlesWithCommentsGetDto;
import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Comment;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
import com.edublog.repository.CommentsRepository;
import com.edublog.usecase.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.edublog.usecase.Pagination;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;
    private final CommentsRepository commentsRepository;
    public final ArticleMapper articleMapper = ArticleMapper.INSTANCE;
    public final CommentMapper commentMapper = CommentMapper.INSTANCE;

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

        return articleMapper.toPatchOutputDto(article.get());
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public ArticlesWithCommentsGetDto getArticleWithCommentsById(Long id, String author) {
        List<CommentGetDto> comments = commentsRepository.findAll()
                .stream().map(commentMapper::toGetDto).toList();
        Article article = articleRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionsTemplate.RESOURCE_NOT_FOUND));
        return articleMapper.toArticlesWithCommentsDto(article, comments);
    }


}
