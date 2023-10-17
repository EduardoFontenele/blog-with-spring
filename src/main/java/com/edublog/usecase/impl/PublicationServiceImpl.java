package com.edublog.usecase.impl;

import com.edublog.adapter.PublicationAdapter;
import com.edublog.domain.dto.publication.PublicationIPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.repository.PublicationRepository;
import com.edublog.usecase.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository repository;
    public final PublicationAdapter publicationMapper = PublicationAdapter.INSTANCE;

    @Override
    public PublicationPostDtoOutput createNewPublication(PublicationIPostDtoInput publication) {
        return null;
    }
}
