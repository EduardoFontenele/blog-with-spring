package com.edublog.validation;

import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountValidator {

    private final AccountRepository accountRepository;

    public void validateUserRegistration(AccountRegisterDto dto) {
        if(accountRepository.findByUsername(dto.getUsername()).isPresent()) throw new BusinessException(ExceptionsTemplate.RESOURCE_ALREADY_EXISTS);
    }

}
