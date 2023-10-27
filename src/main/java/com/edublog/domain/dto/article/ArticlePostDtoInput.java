package com.edublog.domain.dto.article;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ArticlePostDtoInput {
    @NotNull
    @Size(max = 500)
    private String title;

    @NotNull
    @Size(min = 10)
    private String body;
}
