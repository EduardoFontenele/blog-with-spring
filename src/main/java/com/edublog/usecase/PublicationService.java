package com.edublog.usecase;

import com.edublog.domain.dto.publication.PublicationGetDto;
import com.edublog.domain.dto.publication.PublicationPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import org.springframework.data.domain.Page;


public interface PublicationService {
    PublicationPostDtoOutput createNewPublication(PublicationPostDtoInput publication, String username);

    Page<PublicationGetDto> listAllPublications(String author, Integer pageNumber, Integer pageSize);
}
