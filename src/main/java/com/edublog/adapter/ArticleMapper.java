package com.edublog.adapter;

import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.dto.article.ArticlesWithCommentsGetDto;
import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Mapper
public abstract class ArticleMapper {
    public static ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(source = "article.id", target = "id")
    @Mapping(source = "article.title", target = "title")
    @Mapping(source = "article.body", target = "body")
    @Mapping(source = "article.createdAt", target = "createdAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "article.updatedAt", target = "updatedAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "article.account.username", target = "author")
    @Mapping(source = "comments", target = "comments")
    public abstract ArticlesWithCommentsGetDto toArticlesWithCommentsDto(Article article, List<CommentGetDto> comments);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "account.username", target = "author")
    public abstract ArticlePostDtoOutput toPostOutputDto(Article entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "account.username", target = "author")
    public abstract ArticleGetDto toGetDto(Article entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "account.username", target = "author")
    public abstract ArticlePatchDtoOutput toPatchOutputDto(Article entity);

    public Article toEntity(ArticlePostDtoInput dto, Account account) {
        return new Article(dto.getTitle(), dto.getBody(), account);
    }

    @Named("formatLocalDateTime")
    public String formatDateTime(LocalDateTime localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (Objects.isNull(localDate)) {
            return dateTimeFormatter.format(LocalDateTime.now());
        } else {
            return dateTimeFormatter.format(localDate);
        }
    }

}
