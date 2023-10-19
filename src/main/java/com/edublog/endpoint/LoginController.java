package com.edublog.endpoint;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.usecase.RegistrationService;
import com.edublog.validation.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class LoginController {

    private final RegistrationService registrationService;
    private final AccountValidator accountValidator;

    @PostMapping
    public ResponseEntity<AccountInfoDto> registerAccount(@Validated @RequestBody AccountRegisterDto dto) {
        if(accountValidator.checkIfUserExistsByUsername(dto.getUsername()))
            throw new BusinessException(ExceptionsTemplate.RESOURCE_ALREADY_EXISTS);
        return ResponseEntity.ok(registrationService.registerAccount(dto));
    }

}
