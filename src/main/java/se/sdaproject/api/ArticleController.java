package se.sdaproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.model.Article;
import se.sdaproject.repository.ArticleRepository;
import se.sdaproject.service.ResourceNotFoundException;

import java.util.List;


@RestController
public class ArticleController {

    ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @GetMapping("/article")
    public List<Article> listAllArticles() {
        List<Article> article = articleRepository.findAll();
        return article;
    }



    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        System.out.println(article);
        return ResponseEntity.ok(article);
    }


    @PutMapping("/article/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Article article = articleRepository.save(updatedArticle);
        return ResponseEntity.ok(updatedArticle);
    }


    @DeleteMapping("/article/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        articleRepository.delete(article);
        return new ResponseEntity<Article>(HttpStatus.OK);
    }


    @PostMapping("/article")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {

        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

}


