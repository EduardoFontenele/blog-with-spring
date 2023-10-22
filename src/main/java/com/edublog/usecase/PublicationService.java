package com.edublog.usecase;

import com.edublog.domain.dto.publication.PublicationIPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;

public interface PublicationService {
    PublicationPostDtoOutput createNewPublication(PublicationIPostDtoInput publication, String username);
}
