package com.edublog.usecase.impl;

import com.edublog.adapter.ProfileMapper;
import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Profile;
import com.edublog.fixtures.AccountFixture;
import com.edublog.fixtures.ProfileFixture;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.UserProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserProfileServiceImplTest {
    @Mock
    UserProfileRepository userProfileRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    ProfileMapper profileMapper;
    @InjectMocks
    UserProfileServiceImpl userProfileService;

    @Test
    @DisplayName("Given a valid input and existent user, should create a new profile for the account")
    void testCreateNewUserProfileSucceeds() {
        ProfilePostDtoInput input = ProfileFixture.gimmeValidProfilePostDtoInputFixture();
        String username = "username";
        Account account = AccountFixture.gimmeValidAccountFixture();
        Profile profile = ProfileFixture.gimmeValidProfileFixture();
        given(accountRepository.findByUsername(username)).willReturn(Optional.of(account));
        given(userProfileRepository.save(profile)).willReturn(profile);

        ProfilePostDtoOutput result = userProfileService.createNewUserProfile(input, username);

        assertThat(result).isNotNull();
    }
}