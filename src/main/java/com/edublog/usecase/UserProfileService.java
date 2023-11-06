package com.edublog.usecase;


import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.model.Profile;

public interface UserProfileService {
    ProfilePostDtoOutput createNewUserProfile(ProfilePostDtoInput input, String username);
}
