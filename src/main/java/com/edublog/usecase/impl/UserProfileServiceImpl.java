package com.edublog.usecase.impl;

import com.edublog.adapter.ProfileMapper;
import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.dto.profile.ProfileResearchGetDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Profile;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.UserProfileRepository;
import com.edublog.usecase.Pagination;
import com.edublog.usecase.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final AccountRepository accountRepository;
    private final ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    @Override
    public ProfilePostDtoOutput createNewUserProfile(ProfilePostDtoInput userProfile, String username) {
        Account account = accountRepository.findByUsername(username).orElseThrow(() ->
                new BusinessException(ExceptionsTemplate.BAD_REQUEST));
        Profile profile = userProfileRepository.save(profileMapper.toEntity(userProfile, account));
        return profileMapper.toPostOutputDto(profile, username);
    }

    @Override
    public Page<ProfileResearchGetDto> listAll() {
        PageRequest pageRequest = Pagination.buildPageRequest();
        return userProfileRepository.findAll(pageRequest).map(profileMapper::toResearchGetDto);
    }
}
