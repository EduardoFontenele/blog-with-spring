package com.edublog.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ArticleGetDto {
    private Long id;
    private String title;
    private String body;
    private String createdAt;
    private String updatedAt;
    private String author;
}
