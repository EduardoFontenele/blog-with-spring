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
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConfigProject implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(authorityRepository.count() < 3) {
            Authority adminAuthority = authorityRepository.save(Authority.builder().type(AuthorityTable.ADMIN).build());
            Authority userAuthority = authorityRepository.save(Authority.builder().type(AuthorityTable.USER).build());

            authorityRepository.saveAll(Arrays.asList(adminAuthority, userAuthority));
        }

        if(accountRepository.count() < 2) {
            Set<Authority> authorities1 = new HashSet<>();
            authorities1.add(authorityRepository.findByType(AuthorityTable.ADMIN));
            authorities1.add(authorityRepository.findByType(AuthorityTable.USER));

            Account account1 = accountRepository.save(Account.builder()
                        .authorities(authorities1)
                        .username("eduardo.fontenele")
                        .password(passwordEncoder.encode("admin123"))
                    .build());

            Set<Authority> authorities2 = new HashSet<>();
            authorities2.add(authorityRepository.findByType(AuthorityTable.USER));
            Account account2 = accountRepository.save(Account.builder()
                            .authorities(authorities2)
                            .username("mia.m")
                            .password(passwordEncoder.encode("user123"))
                    .build());
        }
    }
}
