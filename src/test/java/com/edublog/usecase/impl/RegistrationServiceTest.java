package com.edublog.usecase.impl;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.domain.enums.AuthorityTable;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Role;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.AuthorityRepository;
import fixture.ValidAccountTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RegistrationServiceTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthorityRepository authorityRepository;
    @InjectMocks
    RegistrationServiceImpl registrationServiceMock;
    final String ROLE_USER = AuthorityTable.ROLE_USER.toString();
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";



    @Test
    @DisplayName("Given a valid input, should register a user")
    void testRegisterAccountSucceeds() {
        //given
        AccountRegisterDto input = AccountRegisterDto.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
        String encodedPwd = passwordEncoder.encode(input.getPassword());
        Role role = Role.builder().type(ROLE_USER).build();
        Account account = new Account(input.getUsername(), encodedPwd, Set.of(role));

        given(authorityRepository.save(role)).willReturn(role);
        given(accountRepository.save(account)).willReturn(account);

        //when
        AccountInfoDto result = registrationServiceMock.registerAccount(input);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(input.getUsername());
    }

    @Test
    @DisplayName("Should disable an account by id")
    void disableAccountByIdSucceeds() {
        //when
        registrationServiceMock.disableAccountById(1L);

        //then
        verify(accountRepository, times(1)).disableAccount(1L);
    }

    @Test
    @DisplayName("Should delete an account by id")
    void testDeleteAccountByIdSucceeds() {
        //when
        registrationServiceMock.deleteAccountById(1L);

        //then
        verify(accountRepository, times(1)).deleteById(1L);
    }
}
