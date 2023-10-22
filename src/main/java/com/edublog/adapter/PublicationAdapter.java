package com.edublog.adapter;

import com.edublog.domain.dto.publication.PublicationIPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.domain.model.Publication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PublicationAdapter {
    public static PublicationAdapter INSTANCE = Mappers.getMapper(PublicationAdapter.class);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "account.username", target = "author")
    public abstract PublicationPostDtoOutput toDto(Publication entity);

}
