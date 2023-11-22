package com.edublog.config;

import com.edublog.domain.model.Account;
import com.edublog.domain.model.Role;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BlogAuthenticationProvider implements AuthenticationProvider {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Account account;

        if(accountRepository.findByUsername(userName).isPresent()) {
            account = accountRepository.findByUsername(userName).get();

            if(!account.getIsEnabled()) throw new BusinessException(ExceptionsTemplate.USER_IS_BANNED);

            if(passwordEncoder.matches(pwd, account.getPassword())) {
                return new UsernamePasswordAuthenticationToken(userName, pwd, getGrantedAuthorities(account.getRoles()));
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("User not found");
        }
    }

    public List<GrantedAuthority> getGrantedAuthorities(Set<Role> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getType()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
