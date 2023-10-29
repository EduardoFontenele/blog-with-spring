package com.edublog.domain.enums;

import lombok.Getter;

@Getter
public enum AuthorityTable {
    ROLE_ADMIN("ADMIN"),
    ROLE_MODERATOR("MODERATOR"),
    ROLE_USER("USER");

    private final String role;

    AuthorityTable(String role) {
        this.role = role;
    }

}
