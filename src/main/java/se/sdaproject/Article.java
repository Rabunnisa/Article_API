package se.sdaproject;

import com.fasterxml.jackson.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String body;
    private String authorName;



    @OneToMany(mappedBy= "article")
    public List<Comments> comments = new ArrayList<>();

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    @ManyToMany(mappedBy = "articles")
   private List<Topic> topics;


    public Article() {

    }

    public Article(String title, String body, String authorName) {
        this.title = title;
        this.body = body;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public List<Comments> getComments() {
        return this.comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }


}