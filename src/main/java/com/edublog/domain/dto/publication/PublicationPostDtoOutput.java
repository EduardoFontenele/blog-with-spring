package com.edublog.domain.dto.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PublicationPostDtoOutput {
    private String title;
    private String body;
    private String createdAt;
    private String updatedAt;
    private String author;
}
