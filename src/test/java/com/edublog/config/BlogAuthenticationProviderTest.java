package com.edublog.config;

import com.edublog.domain.model.Account;
import com.edublog.fixtures.AccountFixture;
import com.edublog.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class BlogAuthenticationProviderTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private BlogAuthenticationProvider authenticationProvider;

    @Test
    @DisplayName("Given a valid existent user, should authenticate successfully")
    public void shouldAuthenticateSuccessfully() {
        // Given
        String username = "validUser";
        String password = "validPassword";
        Account account = AccountFixture.gimmeValidAccountEntityFixture();
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        given(accountRepository.findByUsername(username)).willReturn(Optional.of(account));
        given(passwordEncoder.matches(password, account.getPassword())).willReturn(true);

        // When
        Authentication result = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Then
        assertThat(result).isNotNull();
        assertThat(result.isAuthenticated()).isTrue();
        assertThat(result.getName()).isEqualTo(username);
        verify(passwordEncoder, times(1)).matches(password, account.getPassword());
        verify(accountRepository, times(2)).findByUsername(username);
    }

    @Test
    @DisplayName("Given a valid existent user that passed a wrong password, should throw BadCredentialsException")
    void shouldFailAuthenticateWithWrongPassword() {
        // Given
        String username = "validUser";
        String password = "validPassword";
        String failPassword = "wrongPassword";
        Account account = AccountFixture.gimmeValidAccountEntityFixture();

        given(accountRepository.findByUsername(username)).willReturn(Optional.of(account));
        given(passwordEncoder.matches(failPassword, account.getPassword())).willReturn(false);

        // Then
        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        });
    }

    @Test
    @DisplayName("Given a non existent user, should throw BadCredentialsException")
    void shouldFailAuthenticateWithNonExistentUser() {
        // Given
        String username = "validUser";
        String password = "validPassword";

        given(accountRepository.findByUsername(username)).willReturn(Optional.empty());

        // Then
        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        });
    }
}