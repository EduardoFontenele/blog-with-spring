package com.edublog.fixtures;

import com.edublog.domain.dto.profile.ProfilePostDtoInput;
import com.edublog.domain.model.Profile;

import java.util.UUID;

public class ProfileFixture {
    public static Profile gimmeValidProfileFixture() {
        return Profile.builder()
                .id(UUID.randomUUID().toString()) // Generate a random UUID for id
                .name("John Doe")
                .biography("A brief biography about John Doe.")
                .email("john.doe@example.com")
                .account(AccountFixture.gimmeValidAccountFixture()) // Using the AccountFixture to create an associated Account
                .build();
    }

    public static ProfilePostDtoInput gimmeValidProfilePostDtoInputFixture() {
        ProfilePostDtoInput dtoInput = new ProfilePostDtoInput();
        dtoInput.setName("John Doe");
        dtoInput.setBiography("A brief biography about John Doe.");
        dtoInput.setEmail("john.doe@example.com");
        return dtoInput;
    }
}
