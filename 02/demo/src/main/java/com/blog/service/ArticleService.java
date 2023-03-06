package com.blog.service;

import com.blog.model.dto.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    /**
     * In-memory database
     */
    private final List<Article> articles = new ArrayList<>();
    {
        articles.add(new Article(1L, "First article", "description", "main content"));
        articles.add(new Article(2L, "Second article", "description", "main content"));
    }

    public Optional<Article> getArticleByTitle(String title) {
        return articles.stream().filter(p -> p.getTitle().equals(title))
                .findAny();
    }

    public List<Article> getAll() {
        return articles;
    }

    public Optional<Article> getById(Long id) {
        return articles.stream()
                .filter(p -> p.getId().equals(id))
                .findAny();
    }

    public void save(Article body) {
        articles.add(body);
    }

    public void update(Long id, Article body) {
        Optional<Article> first = articles.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if(first.isPresent()){
            int index = articles.indexOf(first.get());
            articles.set(index, body);
        }
    }

    public void deleteById(Long id) {
        Optional<Article> first = articles.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        first.ifPresent(articles::remove);
    }

    public List<Article> search(String title) {
        return articles.stream()
                .filter(p -> p.getTitle().contains(title))
                .collect(Collectors.toList());
    }
}
