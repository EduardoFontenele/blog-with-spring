package com.edublog.config;

import com.edublog.domain.enums.AuthorityTable;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Role;
import com.edublog.domain.model.Profile;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.AuthorityRepository;
import com.edublog.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConfigProject implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileRepository userProfileRepository;

    @Override
    public void run(String... args) throws Exception {
        loadAuthorities();
        loadAccount();
        loadProfile();
    }

    private void loadAuthorities() {
        if(authorityRepository.count() < 1) {
            Role adminRole = authorityRepository.save(Role.builder().type(AuthorityTable.ROLE_ADMIN.toString()).build());
        }
    }

    private void loadAccount() {
        if(accountRepository.count() < 1) {
            Role role1 = authorityRepository.findByType(AuthorityTable.ROLE_ADMIN.toString());
            Set<Role> authorities1 = new HashSet<>();
            authorities1.add(role1);
            accountRepository.save(Account.builder()
                    .username("eduardo.fontenele")
                    .password(passwordEncoder.encode("admin123"))
                    .isEnabled(true)
                    .roles(authorities1)
                    .build());
        }
    }

    private void loadProfile() {
        if(userProfileRepository.count() < 1) {
            Profile profile1 = Profile.builder()
                    .name("Eduardo Fontenele")
                    .email("eduardofontedev@gmail.com")
                    .biography("Romântico apaixonado sedutor melhor desenvolvedor Java das redondezas.")
                    .account(accountRepository.findAll().get(0))
                    .build();
            userProfileRepository.save(profile1);
        }
    }
}
