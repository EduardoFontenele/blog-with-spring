package com.edublog.validation;

import com.edublog.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ArticleValidatorTest {
    @Mock
    ArticleRepository articleRepository;
    @InjectMocks
    ArticleValidator articleValidator;
    @Test
    @DisplayName("Given an existent article, should return true")
    void articleExistsInDatabaseSucceeds() {
        //given
        Long passedId = 1L;
        //when
        BDDMockito.when(articleRepository.existsById(passedId)).thenReturn(true);
        //then
        assertTrue(articleValidator.articleExistsInDatabase(passedId));
    }

    @Test
    @DisplayName("Given an nonexistent article, should return false")
    void articleExistsInDatabaseFails() {
        //given
        Long passedId = 1L;
        //when
        BDDMockito.when(articleRepository.existsById(passedId)).thenReturn(false);
        //then
        assertFalse(articleValidator.articleExistsInDatabase(passedId));
    }
}