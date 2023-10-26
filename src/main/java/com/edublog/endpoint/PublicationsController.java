package com.edublog.endpoint;

import com.edublog.domain.dto.publication.PublicationPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.usecase.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationsController {

    private final PublicationService publicationService;

    @PostMapping("/createNewPublication")
    public ResponseEntity<PublicationPostDtoOutput> createNewPublication(
            @RequestBody @Validated PublicationPostDtoInput publicationIPostDtoInput,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
            ) {
        return ResponseEntity
                .created(URI.create(""))
                .body(publicationService.createNewPublication(publicationIPostDtoInput, authentication.getName()));
    }


}
