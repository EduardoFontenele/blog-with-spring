package com.edublog.fixtures;

import com.edublog.domain.dto.account.AccountRegisterDto;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class AccountFixture {
    public static Account gimmeValidAccountEntityFixture() {
        return Account.builder()
                .id(1L)
                .username("John Doe")
                .password("validPassword")
                .isEnabled(true)
                .roles(new HashSet<>(Collections.singletonList(new Role(1L, "ROLE_USER"))))
                .articles(new ArrayList<>())
                .build();
    }

    public static AccountRegisterDto gimmeValidAccountRegisterDtoFixture() {
        AccountRegisterDto dto = new AccountRegisterDto();
        dto.setUsername("John Doe");
        dto.setPassword("validPassword");
        return dto;
    }
}
