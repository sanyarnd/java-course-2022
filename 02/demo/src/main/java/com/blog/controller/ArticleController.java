package com.blog.controller;

import com.blog.model.dto.Article;
import com.blog.model.dto.Error;
import com.blog.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    @Operation(description = "Метод позволяет получать список статей")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Article.class))
                            ),
                            description = "Успешный ответ"),
                    @ApiResponse(responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = Error.class)
                            ),
                            description = "Неуспешный ответ")
            }
    )
    public ResponseEntity<List<Article>> getArticles() {
        return ResponseEntity.ok(articleService.getAll());
    }

    @GetMapping("/articles/title")
    public ResponseEntity<Article> getArticleBy(@RequestParam("title") String title) {

        Optional<Article> articleOpt = articleService.getArticleByTitle(title);

        if (articleOpt.isPresent()) {
            return ResponseEntity.ok(articleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id) {
        Optional<Article> articleOpt = articleService.getById(id);

        if (articleOpt.isPresent()) {
            return ResponseEntity.ok(articleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article body) {
        articleService.save(body);
        return ResponseEntity.created(URI.create("/articles")).build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity updateArticle(@PathVariable("id") Long id, @RequestBody Article body) {
        articleService.update(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/articles/search")
    public ResponseEntity<List<Article>> search(@RequestParam("title") String title) {
        List<Article> foundArticles = articleService.search(title);
        return ResponseEntity.ok(foundArticles);
    }
}
