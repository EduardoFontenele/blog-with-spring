package com.edublog.endpoint;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.usecase.RegistrationService;
import com.edublog.validation.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class LoginController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<AccountInfoDto> registerAccount(@RequestBody AccountRegisterDto accountRegisterDto) {
        return ResponseEntity.ok(registrationService.registerAccount(accountRegisterDto));
    }

}
