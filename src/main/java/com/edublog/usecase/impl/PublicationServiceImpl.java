package com.edublog.usecase.impl;

import com.edublog.adapter.PublicationAdapter;
import com.edublog.domain.dto.publication.PublicationGetDto;
import com.edublog.domain.dto.publication.PublicationPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Publication;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.PublicationRepository;
import com.edublog.usecase.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.edublog.usecase.Pagination;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final AccountRepository accountRepository;
    public final PublicationAdapter publicationAdapter = PublicationAdapter.INSTANCE;

    @Override
    public PublicationPostDtoOutput createNewPublication(PublicationPostDtoInput publication, String username) {
        Account user = accountRepository.findByUsername(username).orElseThrow(() ->
                new BusinessException(ExceptionsTemplate.BAD_REQUEST));
        Publication savedPub = publicationRepository.save(publicationAdapter.toEntity(publication, user));
        return publicationAdapter.toPostOutputDto(savedPub);
    }

    @Override
    public Page<PublicationGetDto> listAllPublications(String author, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = Pagination.buildPageRequest(pageNumber, pageSize);
        return publicationRepository.findAll(pageRequest).map(publicationAdapter::toGetDto);
    }

}
