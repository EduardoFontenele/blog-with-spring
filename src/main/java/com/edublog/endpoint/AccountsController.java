package com.edublog.endpoint;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.usecase.RegistrationService;
import com.edublog.validation.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class AccountsController {

    private final RegistrationService registrationService;
    private final AccountValidator accountValidator;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerAccount(@Validated @RequestBody AccountRegisterDto dto) {
        if(accountValidator.accountExistsInDatabase(dto.getUsername()))
            throw new BusinessException(ExceptionsTemplate.USER_ALREADY_EXISTS, dto.getUsername());
        registrationService.registerAccount(dto);
    }

    @PatchMapping("/disable_by_id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableAccountById(@PathVariable("id") Long id) {
        if(accountValidator.accountExistsInDatabaseById(id)) {
            registrationService.disableAccountById(id);
        } else {
            throw new BusinessException(ExceptionsTemplate.RESOURCE_NOT_FOUND);
        }
    }

    @DeleteMapping("/delete_by_id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable Long id) {
        if(accountValidator.accountExistsInDatabaseById(id)) {
            registrationService.deleteAccountById(id);
        } else {
            throw new BusinessException(ExceptionsTemplate.RESOURCE_NOT_FOUND);
        }
    }

}
