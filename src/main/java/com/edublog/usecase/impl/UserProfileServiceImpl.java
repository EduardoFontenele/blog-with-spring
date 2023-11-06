package com.edublog.usecase.impl;

import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Profile;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.UserProfileRepository;
import com.edublog.usecase.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final AccountRepository accountRepository;

    @Override
    public ProfilePostDtoOutput createNewUserProfile(ProfilePostDtoInput userProfile, String username) {
        Account account = accountRepository.findByUsername(username).orElseThrow(() ->
                new BusinessException(ExceptionsTemplate.BAD_REQUEST));

        Profile profile = new Profile();
        profile.setName(userProfile.getName());
        profile.setBiography(userProfile.getBiography());
        profile.setEmail(userProfile.getEmail());
        profile.setAccount(account);

        userProfileRepository.save(profile);

        String articlesWritten;

        if(account.getArticles().isEmpty()) {
            articlesWritten = "0 Artigos";
        } else {
            articlesWritten = String.format("%d Artigos", account.getArticles().size());
        }

        return ProfilePostDtoOutput.builder()
                .username(username)
                .name(profile.getName())
                .email(profile.getEmail())
                .biography(profile.getBiography())
                .articlesWritten(articlesWritten)
                .build();
    }
}
