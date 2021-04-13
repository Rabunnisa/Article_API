package se.sdaproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.model.Article;
import se.sdaproject.model.Topic;
import se.sdaproject.repository.ArticleRepository;
import se.sdaproject.repository.TopicRepository;
import se.sdaproject.service.ResourceNotFoundException;

import java.util.List;

@RestController
public class TopicController {
    TopicRepository topicRepository;
    ArticleRepository articleRepository;


   @Autowired
    public TopicController(TopicRepository topicRepository, ArticleRepository articleRepository) {
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }


@PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic){
    topicRepository.save(topic);
    return ResponseEntity.status(HttpStatus.CREATED).body(topic);
}

@PostMapping("/article/{articleId}/topics")
    public ResponseEntity<Topic> addTopicToTheArticle(@PathVariable Long articleId ,@RequestBody Topic topics){
    //Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
    Article article=   articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
    Topic topicsSearch = topicRepository.findByNames(topics.getNames()).orElse(topics);


    List<Article> topicArticles = topicsSearch.getArticles();
    topicArticles.add(article);

    return ResponseEntity.status(HttpStatus.CREATED).body(topicRepository.save(topicsSearch));
}

@GetMapping("/topics")
public List<Topic> listAllTopics()
{ List<Topic> topics= topicRepository.findAll();
return topics;
}

@GetMapping("/articles/{articleId}/topics")
    public ResponseEntity<List<Topic >> listTopicsByArticleId(@PathVariable Long articleId){
          Article article=   articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
              List<Topic> topics = article.getTopics();
          return ResponseEntity.ok(topics)     ;
}

@GetMapping("/topics/{topicId}/articles")
    public ResponseEntity<List<Article >> listArticlesByTopicId(@PathVariable Long topicId) {
    Topic topics = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
    List<Article> article = topics.getArticles();

    return ResponseEntity.ok(article);
}


 @DeleteMapping("articles/{articleId}/topics/{topicsId}")
 @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteTopicsWithArticleId(@PathVariable Long articleId, @PathVariable Long topicsId) {
                    Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
                    Topic topics = topicRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
                  if (topics.getArticles().contains(article)) {
                      topics.getArticles().remove(article);
                      topicRepository.save(topics);
                  } else{
                      throw new ResourceNotFoundException();
                  }
   }

   @DeleteMapping("topics/{topicsId}")
      @ResponseStatus(HttpStatus.NO_CONTENT)
       public void deleteTopic(@PathVariable Long topicsId) {
        Topic topics = topicRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        topicRepository.delete(topics);
            }                         }