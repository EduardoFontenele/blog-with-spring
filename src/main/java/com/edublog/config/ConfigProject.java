package com.edublog.config;

import com.edublog.domain.enums.AuthorityTable;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Authority;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConfigProject implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(authorityRepository.count() < 1) {
            Authority adminAuthority = authorityRepository.save(Authority.builder().type(AuthorityTable.ROLE_ADMIN.toString()).build());
            authorityRepository.saveAll(List.of(adminAuthority));
        }

        if(accountRepository.count() < 1) {
            Authority authority1 = authorityRepository.findByType(AuthorityTable.ROLE_ADMIN.toString());

            Set<Authority> authorities1 = new HashSet<>();
            authorities1.add(authority1);

            accountRepository.save(Account.builder()
                    .username("eduardo.fontenele")
                    .password(passwordEncoder.encode("admin123"))
                    .isEnabled(true)
                    .authorities(authorities1)
                    .build());
        }
    }
}
