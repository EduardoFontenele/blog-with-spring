package com.edublog.domain.dto.article;

import com.edublog.domain.dto.comment.CommentGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ArticlesWithCommentsGetDto {
    private Long id;
    private String title;
    private String body;
    private String createdAt;
    private String updatedAt;
    private String author;
    private List<CommentGetDto> comments;
}
