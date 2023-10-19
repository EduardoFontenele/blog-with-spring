package com.edublog.validation;

import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class AccountValidator {

    private final AccountRepository accountRepository;

    public boolean checkIfUserExistsByUsername(String username) {
       return accountRepository.existsByUsername(username);
    }

}
