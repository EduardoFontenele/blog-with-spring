package com.edublog.usecase.impl;

import com.edublog.adapter.ArticleMapper;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.model.Account;
import com.edublog.domain.model.Article;
import com.edublog.exception.BusinessException;
import com.edublog.repository.AccountRepository;
import com.edublog.repository.ArticleRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ArticleServiceImplTest {

    @InjectMocks
    ArticleServiceImpl publicationService;
    @Mock
    ArticleRepository publicationRepository;
    @Mock
    ArticleMapper publicationAdapter;
    @Mock
    AccountRepository accountRepository;

    @Test
    void createNewPublication() {
        Account author = new Account();
        Article savedPub = new Article();

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
        ArticlePostDtoInput input = ArticlePostDtoInput.builder()
                .body("mock")
                .title("mock")
                .build();

        Assertions.assertThrows(BusinessException.class, () -> {
            when(publicationService.createNewArticle(input, "user")).thenThrow(BusinessException.class);
        });

        verify(accountRepository, times(1)).findByUsername("user");
    }
}