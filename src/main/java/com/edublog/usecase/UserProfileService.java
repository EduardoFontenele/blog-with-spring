package com.edublog.usecase;


import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.dto.profile.ProfileResearchGetDto;
import com.edublog.domain.model.Profile;
import org.springframework.data.domain.Page;

public interface UserProfileService {
    ProfilePostDtoOutput createNewUserProfile(ProfilePostDtoInput input, String username);

    Page<ProfileResearchGetDto> listAll();
}
