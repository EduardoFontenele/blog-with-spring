package com.edublog.domain.dto.account;

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
public class AccountRegisterDto {
    @NotNull
    @Size(min = 0, max = 255)
    private String username;

    @NotNull
    @Size(min = 0, max = 255)
    private String password;
}
