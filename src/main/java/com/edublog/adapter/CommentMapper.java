package com.edublog.adapter;

import com.edublog.domain.dto.comment.CommentGetDto;
import com.edublog.domain.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public abstract class CommentMapper {

    public static CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatLocalDateTime")
    public abstract CommentGetDto toGetDto(Comment entity);

    @Named("formatLocalDateTime")
    public String formatDateTime(LocalDateTime localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTimeFormatter.format(localDate);
    }


}
