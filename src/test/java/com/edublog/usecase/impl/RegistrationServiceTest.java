package com.edublog.usecase.impl;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.domain.model.Account;
import com.edublog.repository.AccountRepository;
import fixture.ValidAccountTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RegistrationServiceTest {
    @InjectMocks
    RegistrationServiceImpl registrationServiceMock;
    @Mock
    AccountRepository accountRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    AccountRegisterDto input;
    AccountInfoDto output;

    @BeforeEach
    void setUp() {
        input = AccountRegisterDto.builder()
                .username(ValidAccountTemplate.USERNAME)
                .password(ValidAccountTemplate.PASSWORD)
                .build();
    }

    @Test
    void testRegisterAccount() {
       output = registrationServiceMock.registerAccount(input);

       verify(accountRepository, times(1)).save(any(Account.class));
       Assertions.assertNotNull(output);
       Assertions.assertEquals(input.getUsername(), output.getUsername());
    }

}
