package com.edublog.usecase.impl;

import com.edublog.adapter.PublicationAdapter;
import com.edublog.domain.dto.publication.PublicationIPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Publication;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.PublicationRepository;
import com.edublog.usecase.PublicationService;
import com.edublog.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final AccountRepository accountRepository;
    public final PublicationAdapter publicationMapper = PublicationAdapter.INSTANCE;

    @Override
    public PublicationPostDtoOutput createNewPublication(PublicationIPostDtoInput publication, String username) {
        Account user = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new BusinessException(ExceptionsTemplate.BAD_REQUEST));

        Publication savedPub = publicationRepository
                .save(new Publication(publication.getBody(), publication.getTitle(), user));

        return publicationMapper.toDto(savedPub);
    }
}
