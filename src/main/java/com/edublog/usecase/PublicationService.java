package com.edublog.usecase;

import com.edublog.domain.dto.publication.PublicationPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;

public interface PublicationService {
    PublicationPostDtoOutput createNewPublication(PublicationPostDtoInput publication, String username);
}
