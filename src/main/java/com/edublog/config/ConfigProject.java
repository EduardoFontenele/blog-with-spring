package com.edublog.config;

import com.edublog.domain.model.Account;
import com.edublog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfigProject implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        accountRepository.save(Account.builder()
                        .role("ADMIN")
                        .username("eduardo.fontenele")
                        .password(passwordEncoder.encode("admin123"))
                .build());
    }
}
