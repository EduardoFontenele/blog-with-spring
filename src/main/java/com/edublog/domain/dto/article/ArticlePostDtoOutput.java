package com.edublog.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ArticlePostDtoOutput {
    private String title;
    private String body;
    private String createdAt;
    private String updatedAt;
    private String author;
}
