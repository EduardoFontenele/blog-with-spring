package com.edublog.usecase.impl;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.domain.enums.AuthorityTable;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Authority;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.AuthorityRepository;
import com.edublog.usecase.RegistrationService;
import com.edublog.validation.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    private final String ROLE_USER = AuthorityTable.ROLE_USER.toString();

    @Override
    public AccountInfoDto registerAccount(AccountRegisterDto accountRegisterDto) {
        String encodedPassword = passwordEncoder.encode(accountRegisterDto.getPassword());
        Authority authority = Authority.builder().type(ROLE_USER).build();

        authorityRepository.save(authority);
        accountRepository.save(new Account(accountRegisterDto.getUsername(), encodedPassword, Set.of(authority)));
        return new AccountInfoDto(accountRegisterDto.getUsername());
    }

    @Override
    @Transactional
    public void disableAccountById(Long id) {
        accountRepository.disableAccount(id);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

}
