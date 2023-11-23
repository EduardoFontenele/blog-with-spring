package com.edublog.validation;

import com.edublog.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountValidatorTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountValidator accountValidator;

    @Test
    @DisplayName("Given existent user validated by username, should return true")
    void accountExistsInDatabaseSucceeds() {
        String passedUser = "user123";

        when(accountValidator.accountExistsInDatabase(passedUser)).thenReturn(true);
        boolean result = accountValidator.accountExistsInDatabase(passedUser);

        assertTrue(result);
        verify(accountRepository, times(1)).existsByUsername(passedUser);
    }

    @Test
    @DisplayName("Given nonexistent user validated by username, should return true")
    void accountExistsInDatabaseFails() {
        String passedUser = "user123";

        when(accountValidator.accountExistsInDatabase(passedUser)).thenReturn(false);
        boolean result = accountValidator.accountExistsInDatabase(passedUser);

        assertFalse(result);
        verify(accountRepository, times(1)).existsByUsername(passedUser);
    }

    @Test
    @DisplayName("Given existent user validated by id, should return true")
    void accountExistsInDatabaseByIdSucceeds() {
        Long passedId = 1L;

        when(accountValidator.accountExistsInDatabaseById(passedId)).thenReturn(true);
        boolean result = accountValidator.accountExistsInDatabaseById(passedId);

        assertTrue(result);
        verify(accountRepository, times(1)).existsById(passedId);
    }

    @Test
    @DisplayName("Given nonexistent user validated by id, should return false")
    void accountExistsInDatabaseByIdFails() {
        Long passedId = 1L;

        when(accountValidator.accountExistsInDatabaseById(passedId)).thenReturn(false);
        boolean result = accountValidator.accountExistsInDatabaseById(passedId);

        assertFalse(result);
        verify(accountRepository, times(1)).existsById(passedId);
    }
}