package com.edublog.endpoint;

import com.edublog.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/principal")
public class PrincipalController {

    // Funciona
    @GetMapping("/checkPrincipalObject")
    public String principal(Principal principal) {
        return Objects.isNull(principal) ? "null" : principal.getName();
    }

    // Não funciona
    @GetMapping("/checkAuthenticationPrincipal")
    public String currentUserName(@AuthenticationPrincipal Account account) {
        return Objects.isNull(account) ? "null" : account.getUsername();
    }

    // Funciona
    @GetMapping("/checkCurrentAuthenticationSomething")
    public String currentUserName(@CurrentSecurityContext(expression = "authentication")
                                  Authentication authentication) {
        return Objects.isNull(authentication) ? "null" : authentication.getName();
    }
}
