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
            Authority adminAuthority = authorityRepository.save(Authority.builder().type(AuthorityTable.ROLE_ADMIN.toString()).build());
            Authority moderatorAuthority = authorityRepository.save(Authority.builder().type(AuthorityTable.ROLE_MODERATOR.toString()).build());
            Authority userAuthority = authorityRepository.save(Authority.builder().type(AuthorityTable.ROLE_USER.toString()).build());


            authorityRepository.saveAll(Arrays.asList(adminAuthority, userAuthority, userAuthority));
        }

        if(accountRepository.count() < 2) {
            Authority authority1 = authorityRepository.findByType(AuthorityTable.ROLE_ADMIN.toString());
            Authority authority2 = authorityRepository.findByType(AuthorityTable.ROLE_MODERATOR.toString());
            Authority authority3 = authorityRepository.findByType(AuthorityTable.ROLE_USER.toString());

            accountRepository.save(Account.builder()
                    .authority(authority1)
                    .username("eduardo.fontenele")
                    .password(passwordEncoder.encode("admin123"))
                    .build());
            accountRepository.save(Account.builder()
                    .authority(authority2)
                    .username("john.doe")
                    .password(passwordEncoder.encode("mod123"))
                    .build());
            accountRepository.save(Account.builder()
                    .authority(authority3)
                    .username("zeh_da_manga")
                    .password(passwordEncoder.encode("user123"))
                    .build());
        }
    }
}
