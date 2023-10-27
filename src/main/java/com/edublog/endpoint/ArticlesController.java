package com.edublog.endpoint;

import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePatchDtoInput;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.usecase.ArticleService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticlesController {

    private final ArticleService articleService;

    @PostMapping("/create_new")
    public ResponseEntity<ArticlePostDtoOutput> createNewArticle(
            @RequestBody @Validated ArticlePostDtoInput publicationIPostDtoInput,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
            ) {
        return ResponseEntity
                .created(URI.create(""))
                .body(articleService.createNewArticle(publicationIPostDtoInput, authentication.getName()));
    }

    @GetMapping("/list_all")
    public ResponseEntity<Page<ArticleGetDto>> listAll(
            @CurrentSecurityContext(expression = "authentication") Authentication authentication,
            @Validated @RequestParam(required = false) @Min(1) Integer pageNumber,
            @Validated @RequestParam(required = false) @Min(1) Integer pageSize) {
        return ResponseEntity.ok(articleService.listAllArticles(authentication.getName(), pageNumber, pageSize));
    }

    @PatchMapping("/update_by_id/{id}")
    public ResponseEntity<ArticlePatchDtoOutput> updateById(
            @PathVariable("id") Long id,
            @Validated @RequestBody ArticlePatchDtoInput dtoInput
    ) {
        return ResponseEntity.ok(articleService.updateArticle(dtoInput, id));
    }
}