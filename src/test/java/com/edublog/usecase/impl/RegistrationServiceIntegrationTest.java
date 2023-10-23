package com.edublog.usecase.impl;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.domain.model.Account;
import com.edublog.repository.AccountRepository;
import com.edublog.usecase.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationServiceIntegrationTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    PasswordEncoder passwordEncoder;
    AccountRegisterDto input;
    AccountInfoDto output;
    Account entity;

    @BeforeEach
    void setUp() {
        input = new AccountRegisterDto("user", passwordEncoder.encode("user123"));
    }

    @Test
    @DisplayName("Given an valid input dto account, should and return an output dto")
    void testRegisterAccountMethodReturnObject() {
        output = registrationService.registerAccount(input);

        assertEquals(input.getUsername(), output.getUsername());
    }

    @Test
    @DisplayName("Given an valid input dto, should persist the data in the database and return object")
    void testIfDataIsBeingPersistedCorrectly() {
        entity = accountRepository.save(new Account(input.getUsername(), input.getPassword(), "USER"));

        assertEquals(entity.getUsername(), input.getUsername());
        assertEquals(entity.getPassword(), input.getPassword());
        assertEquals(entity.getRole(), "USER");
    }
}