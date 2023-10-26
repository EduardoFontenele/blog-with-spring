package com.edublog.usecase.impl;

import com.edublog.adapter.PublicationAdapter;
import com.edublog.domain.dto.publication.PublicationPostDtoInput;
import com.edublog.domain.dto.publication.PublicationPostDtoOutput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Publication;
import com.edublog.exception.BusinessException;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.PublicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PublicationServiceImplTest {

    @InjectMocks
    PublicationServiceImpl publicationService;
    @Mock
    PublicationRepository publicationRepository;
    @Mock
    PublicationAdapter publicationAdapter;
    @Mock
    AccountRepository accountRepository;

    @Test
    void createNewPublication() {
        Account author = new Account();
        Publication savedPub = new Publication();

        given(accountRepository.findByUsername("user")).willReturn(Optional.of(author));
        given(publicationRepository.save(savedPub)).willReturn(savedPub);

        author = accountRepository.findByUsername("user").get();
        savedPub = publicationRepository.save(savedPub);

        verify(accountRepository, times(1)).findByUsername("user");
        verify(publicationRepository, times(1)).save(savedPub);

        Assertions.assertNotNull(author);
        Assertions.assertNotNull(savedPub);
    }

    @Test
    void createNewPublicationException() {
        PublicationPostDtoInput input = PublicationPostDtoInput.builder()
                .body("mock")
                .title("mock")
                .build();

        Assertions.assertThrows(BusinessException.class, () -> {
            when(publicationService.createNewPublication(input, "user")).thenThrow(BusinessException.class);
        });

        verify(accountRepository, times(1)).findByUsername("user");
    }
}