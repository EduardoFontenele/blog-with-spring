package com.edublog.domain.dto.profile;

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
public class ProfilePostDtoInput {
    @NotNull(message = "Name can't be NULL")
    @Size(min = 3, max = 255, message = "Username must be 3-255 characters long")
    private String name;

    @Size(max = 2000, message = "Biography can't be greater than 2000 chars")
    private String biography;

    private String email;
}
