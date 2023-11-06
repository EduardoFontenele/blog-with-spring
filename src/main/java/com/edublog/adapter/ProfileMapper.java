package com.edublog.adapter;

import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ProfileMapper {
    public static ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    public Profile toEntity(ProfilePostDtoInput input, Account account) {
        return new Profile(input.getName(), input.getBiography(), input.getEmail(), account);
    };

    @Mapping(source = "username", target = "username")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.biography", target = "biography")
    @Mapping(source = "entity.email", target = "email")
    @Mapping(source = "account.articles", target = "articlesWritten", qualifiedByName = "displayArticlesWritten")
    public abstract ProfilePostDtoOutput toPostOutputDto(Profile entity, String username, Account account);

    @Named("displayArticlesWritten")
    public String displayArticlesWritten(List<Article> articles) {
        return articles.isEmpty() ? "0 Artigos" : String.format("%d Artigos", articles.size());
    }
}
