package com.edublog.usecase.impl;

import com.edublog.adapter.ProfileMapper;
import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.dto.profile.ProfileResearchGetDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Profile;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.fixtures.AccountFixture;
import com.edublog.fixtures.ProfileFixture;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.UserProfileRepository;
import com.edublog.usecase.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserProfileServiceTest {
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
        Account account = AccountFixture.gimmeValidAccountEntityFixture();
        Profile profile = ProfileFixture.gimmeValidProfileFixture();
        given(accountRepository.findByUsername(username)).willReturn(Optional.of(account));
        given(userProfileRepository.save(profile)).willReturn(profile);

        ProfilePostDtoOutput result = userProfileService.createNewUserProfile(input, username);

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Given a valid input and nonexistent user, should throw BusinessException of type BAD REQUEST")
    void testCreateNewUserFailsDueToNonexistentAccount() {
        ProfilePostDtoInput input = ProfileFixture.gimmeValidProfilePostDtoInputFixture();
        String username = "username";

        given(accountRepository.findByUsername(username)).willReturn(Optional.empty());

        BusinessException result = assertThrows(BusinessException.class, () -> {
           userProfileService.createNewUserProfile(input, username);
        });

        assertThat(result).isNotNull();
        assertThat(result.getHttpStatus()).isEqualTo(ExceptionsTemplate.BAD_REQUEST.getHttpStatus());
    }

    @Test
    @DisplayName("Should return a page of ProfileResearchGetDto")
    void testListAllSucceeds() {
        //given
        PageRequest pageRequest = Pagination.buildPageRequest(1, 1);
        Page<Profile> profilePage = new PageImpl<>(
                List.of(ProfileFixture.gimmeValidProfileFixture()),
                pageRequest,
                1);

        given(userProfileRepository.findAll(pageRequest)).willReturn(profilePage);
        //when
        Page<ProfileResearchGetDto> result = userProfileService.listAll();

        //then
        assertThat(result).isNotNull();
        assertThat(result.getContent().get(0).getUsername()).isEqualTo(ProfileFixture.gimmeValidProfileFixture().getName());
    }
}