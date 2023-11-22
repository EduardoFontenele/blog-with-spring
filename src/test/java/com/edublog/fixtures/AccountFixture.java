package com.edublog.fixtures;

import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.domain.model.Role;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class AccountFixture {
    public static Account gimmeValidAccountFixture() {
        return Account.builder()
                .username("validUser")
                .password("validPassword")
                .isEnabled(true)
                .roles(new HashSet<>(Collections.singletonList(new Role(1L, "ROLE_USER"))))
                .articles(List.of(new Article()))
                .build();
    }
}
