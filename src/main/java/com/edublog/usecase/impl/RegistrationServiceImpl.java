package com.edublog.usecase.impl;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.domain.model.Account;
import com.edublog.repository.AccountRepository;
import com.edublog.usecase.RegistrationService;
import com.edublog.validation.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountInfoDto registerAccount(AccountRegisterDto accountRegisterDto) {
        String encodedPassword = passwordEncoder.encode(accountRegisterDto.getPassword());
        accountRepository.save(new Account(accountRegisterDto.getUsername(), encodedPassword, "USER"));
        return new AccountInfoDto(accountRegisterDto.getUsername());
    }
}
