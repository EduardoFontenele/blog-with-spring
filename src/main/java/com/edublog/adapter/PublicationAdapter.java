package com.edublog.adapter;

import com.edublog.domain.dto.publication.PublicationGetDto;
import com.edublog.domain.dto.publication.PublicationPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Publication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public abstract class PublicationAdapter {
    public static PublicationAdapter INSTANCE = Mappers.getMapper(PublicationAdapter.class);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "formatDateTime")
    @Mapping(source = "account.username", target = "author")
    public abstract PublicationPostDtoOutput toPostOutputDto(Publication entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "formatDateTime")
    @Mapping(source = "account.username", target = "author")
    public abstract PublicationGetDto toGetDto(Publication entity);

    public Publication toEntity(PublicationPostDtoInput dto, Account account) {
        return new Publication(dto.getTitle(), dto.getBody(), account);
    }

    @Named("formatDateTime")
    public String formatDateTime(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(localDate);
    }

}
