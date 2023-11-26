package com.edublog.endpoint;

import com.edublog.domain.dto.article.ArticleGetDto;
import com.edublog.domain.dto.article.ArticlePatchDtoInput;
import com.edublog.domain.dto.article.ArticlePatchDtoOutput;
import com.edublog.domain.dto.article.ArticlePostDtoInput;
import com.edublog.domain.dto.article.ArticlePostDtoOutput;
import com.edublog.domain.dto.article.ArticlesWithCommentsGetDto;
import com.edublog.exception.BusinessException;
import com.edublog.exception.ExceptionsTemplate;
import com.edublog.usecase.ArticleService;
import com.edublog.validation.ArticleValidator;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/articles")
public class ArticlesController {

    private final ArticleService articleService;
    private final ArticleValidator articleValidator;

    @PostMapping("/create_new")
    public ResponseEntity<ArticlePostDtoOutput> createNewArticle(
            @CurrentSecurityContext(expression = "authentication") Authentication authentication,
            @RequestBody @Validated ArticlePostDtoInput publicationIPostDtoInput
            ) {

        return ResponseEntity
                .created(URI.create(""))
                .body(articleService.createNewArticle(publicationIPostDtoInput, authentication.getName()));
    }

    @GetMapping("/list_all")
    public ResponseEntity<Page<ArticleGetDto>> listAll(
            @CurrentSecurityContext(expression = "authentication") Authentication authentication,
            @Validated @RequestParam(required = false) @Min(1) Integer pageNumber,
            @Validated @RequestParam(required = false) @Min(1) Integer pageSize
    ) {
        Page<ArticleGetDto> returnedPage = articleService.listAllArticles(authentication.getName(), pageNumber, pageSize);
        return ResponseEntity.ok(returnedPage);
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<ArticlesWithCommentsGetDto> getArticleWithCommentsById(
            @CurrentSecurityContext(expression = "authentication") Authentication authentication,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(articleService.getArticleWithCommentsById(id, authentication.getName()));
    }

    @PatchMapping("/update_by_id/{id}")
    public ResponseEntity<ArticlePatchDtoOutput> updateById(
            @PathVariable("id") Long id,
            @Validated @RequestBody ArticlePatchDtoInput dtoInput
    ) {
        if(!articleValidator.articleExistsInDatabase(id)) throw new BusinessException(ExceptionsTemplate.RESOURCE_NOT_FOUND);
        return ResponseEntity.ok(articleService.updateArticle(dtoInput, id));
    }

    @DeleteMapping("/delete_by_id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        if(!articleValidator.articleExistsInDatabase(id)) throw new BusinessException(ExceptionsTemplate.RESOURCE_NOT_FOUND);
        articleService.deleteById(id);
    }
}
