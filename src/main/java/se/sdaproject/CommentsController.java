package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommentsController {
    CommentRepository commentsRepository;
    ArticleRepository articleRepository;

    @Autowired
    public CommentsController(CommentRepository commentsRepository, ArticleRepository articleRepository) {
        this.commentsRepository = commentsRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("/article/{articleId}/comments")
    public ResponseEntity<Comments> createComment(@PathVariable Long articleId, @RequestBody  Comments comments){
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);

        comments.setArticle(article);

        commentsRepository.save(comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comments> updateComment(@PathVariable Long id,  @RequestBody Comments updatedComment) {
        Comments comments = commentsRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(id);
        updatedComment.setArticle(comments.getArticle());

        commentsRepository.save(updatedComment);
        return ResponseEntity.ok(updatedComment);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comments> deleteComment(@PathVariable Long id){
        Comments comments = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        commentsRepository.delete(comments);
        return  ResponseEntity.ok(comments);
    }
    /*@GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<Comments >>  getAllCommentsToParticularArticle(@PathVariable Long articleId  , @RequestBody Comments comments)  {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        articleRepository.findByComment(comments);
        return ResponseEntity.ok(articleRepository.findByComment( comments));
    }*/

    @GetMapping(value = "/comments", params = {"author"})
    public ResponseEntity<List<Comments>> getCommentForParticularAuthor(@RequestParam String author){
        return ResponseEntity.ok(commentsRepository.findByAuthor(author));
    }

}
