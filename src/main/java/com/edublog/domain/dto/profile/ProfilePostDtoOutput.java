package com.edublog.domain.dto.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ProfilePostDtoOutput {
    private String username;
    private String name;
    private String biography;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String articlesWritten;
}
