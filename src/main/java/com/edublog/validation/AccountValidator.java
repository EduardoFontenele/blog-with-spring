package com.edublog.validation;

import com.edublog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountValidator {

    private final AccountRepository accountRepository;

    public boolean accountExistsInDatabase(String username) {
       return accountRepository.existsByUsername(username);
    }
    public boolean accountExistsInDatabaseById(Long id) {
        return accountRepository.existsById(id);
    }

}
