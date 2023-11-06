package com.edublog.config;

import com.edublog.domain.enums.AuthorityTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class BlogSecurityConfig {
    private final String ADMIN = AuthorityTable.ROLE_ADMIN.getRole();
    private final String MOD = AuthorityTable.ROLE_MODERATOR.getRole();
    private final String USER = AuthorityTable.ROLE_USER.getRole();

    @Bean
    DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                "api/accounts/register",
                                "api/articles/list_all",
                                "api/users/find_users"
                        ).permitAll()
                        .requestMatchers(
                                "api/articles/create_new",
                                "api/articles/update_by_id/*",
                                "api/articles/delete_by_id/*",
                                "api/users/create_new"
                        ).hasAnyRole(ADMIN, MOD, USER)
                        .requestMatchers(
                                "api/admin/disable_by_id/*",
                                "api/admin/delete_by_id/*"
                        ).hasRole(ADMIN))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
