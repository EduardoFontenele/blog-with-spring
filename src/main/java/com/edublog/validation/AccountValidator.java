package com.edublog.validation;

import com.edublog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountValidator {

    private final AccountRepository accountRepository;

    public boolean userExistsInDatabase(String username) {
       return accountRepository.existsByUsername(username);
    }

}
