package com.edublog.adapter;

import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.domain.model.Publication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublicationAdapter {
    PublicationAdapter INSTANCE = Mappers.getMapper(PublicationAdapter.class);


    PublicationPostDtoOutput publicationEntityToPostDtoOutput(Publication entity);
}
