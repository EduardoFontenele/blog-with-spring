package com.edublog.mapper;

import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public abstract class ArticleMapper {
    public static ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

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
        return dateTimeFormatter.format(localDate);
    }

}
