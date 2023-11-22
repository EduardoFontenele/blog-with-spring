package com.edublog.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ArticlePostDtoOutput {
    private String title;
    private String body;
    private String createdAt;
    private String updatedAt;
    private String author;
}
