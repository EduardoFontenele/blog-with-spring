package com.edublog.usecase.impl;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.usecase.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class RegistrationServiceIT {


    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private AccountRegisterDto input = AccountRegisterDto.builder()
            .username("user")
            .password("1234")
            .build();
    private AccountInfoDto output;

    @Test
    @DisplayName("Given an valid input Account DTO, should return an output Account DTO")
    void testRegisterAccountReturnValues() {
        output = registrationService.registerAccount(input);
        Assertions.assertEquals(input.getUsername(), output.getUsername());
    }


}