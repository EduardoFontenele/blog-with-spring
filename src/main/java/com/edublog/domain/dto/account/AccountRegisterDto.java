package com.edublog.domain.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AccountRegisterDto {
    @NotNull(message = "Username can't be NULL")
    @Size(min = 3, max = 255, message = "Username must be 3-255 characters long")
    private String username;

    @NotNull(message = "Password can't be NULL")
    @Size(min = 5, max = 255, message = "Password must be 5-255 characters long")
    private String password;
}
