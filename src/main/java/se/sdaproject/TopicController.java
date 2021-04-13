package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topics){
    topicRepository.save(topics);
    return ResponseEntity.status(HttpStatus.CREATED).body(topics);
}

@PostMapping("/article/{articleId}/topics/{topicId}")
    public ResponseEntity<Topic> addTopicToTheArticle(  @PathVariable Long articleId, @PathVariable Long topicId){
    Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
    Article article=   articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);

    topic.getArticles().add(article);
    topicRepository.save(topic);
    return ResponseEntity.status(HttpStatus.CREATED).body(topic);
}

@GetMapping("/topic")
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
                    Topic topic = topicRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
                  if (article.getTopics().contains(article)) {
                      topic.getArticles().remove(topic);
                                  topicRepository.save(topic);
                              } else{
                  }               throw new ResourceNotFoundException();          }



  @DeleteMapping("topics/{topicsId}")
      @ResponseStatus(HttpStatus.NO_CONTENT)
       public void deleteTopic(@PathVariable Long topicsId) {
        Topic topics = topicRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        topicRepository.delete(topics);
            }                         }