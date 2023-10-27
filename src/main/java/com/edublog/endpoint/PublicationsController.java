package com.edublog.endpoint;

import com.edublog.domain.dto.publication.PublicationGetDto;
import com.edublog.domain.dto.publication.PublicationPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.usecase.PublicationService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationsController {

    private final PublicationService publicationService;

    @PostMapping("/create_new")
    public ResponseEntity<PublicationPostDtoOutput> createNewPublication(
            @RequestBody @Validated PublicationPostDtoInput publicationIPostDtoInput,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
            ) {
        return ResponseEntity
                .created(URI.create(""))
                .body(publicationService.createNewPublication(publicationIPostDtoInput, authentication.getName()));
    }

    @GetMapping("/list_all")
    public ResponseEntity<Page<PublicationGetDto>> listAll(
            @CurrentSecurityContext(expression = "authentication") Authentication authentication,
            @Validated @RequestParam(required = false) @Min(1) Integer pageNumber,
            @Validated @RequestParam(required = false) @Min(1) Integer pageSize) {
        return ResponseEntity.ok(publicationService.listAllPublications(authentication.getName(), pageNumber, pageSize));
    }

    @PatchMapping("/update_by_id/{id}")
    public ResponseEntity<PublicationGetDto> updateById(@PathVariable("id") Long id) {return null;}
}
