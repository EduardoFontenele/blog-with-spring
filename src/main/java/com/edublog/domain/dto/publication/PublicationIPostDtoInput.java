package com.edublog.domain.dto.publication;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PublicationIPostDtoInput {
    @NotNull
    @Size(max = 500)
    private String title;

    @NotNull
    @Size(min = 10)
    private String body;
}
