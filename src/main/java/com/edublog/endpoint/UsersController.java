package com.edublog.endpoint;

import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.dto.profile.ProfilePostDtoOutput;
import com.edublog.domain.dto.profile.ProfileResearchGetDto;
import com.edublog.domain.model.Profile;
import com.edublog.usecase.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UsersController {

    private final UserProfileService userProfileService;

    @PostMapping("/create_new")
    public ProfilePostDtoOutput createNewUserProfile(
            @RequestBody @Validated ProfilePostDtoInput userProfile,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return userProfileService.createNewUserProfile(userProfile, authentication.getName());
    }

    @GetMapping("/find_users")
    public ResponseEntity<Page<ProfileResearchGetDto>> listAllUsers() {
       return ResponseEntity.ok(userProfileService.listAll());
    }
}
