package com.edublog.validation;

import com.edublog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Component
@RequiredArgsConstructor
public class ArticleValidator {

    private final ArticleRepository articleRepository;

    public boolean articleExistsInDatabase(Long id) {
        return articleRepository.existsById(id);
    }

}
