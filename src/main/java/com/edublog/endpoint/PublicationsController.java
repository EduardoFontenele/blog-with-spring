package com.edublog.endpoint;

import com.edublog.domain.dto.publication.PublicationIPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationsController {



    @PostMapping("/createNewPublication")
    public ResponseEntity<PublicationPostDtoOutput> createNewPublication(@RequestBody @Validated PublicationIPostDtoInput publicationIPostDtoInput) {
        return null;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }

}
