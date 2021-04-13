# Article_API
This API is used to create various articles with comments and distinguish it with various topics

## Introduction
This Project is all about building the backend API for a developer news site where users can create articles, comment them and post their reactions (likes, dislikes). It doesn't require a graphical user interface so it is enough to be able to make requests and get plain json text responses via curl/Postman. 

## Learning Objectives
* Understand the basic structure of a Spring application.
* Practice building, testing and consuming rest APIs.
* Learn about data modelling for real world applications.
* Learn how to interact with a relational database using an ORM tool implementing Spring JPA (Hibernate).

##  Remember to Setup before you start
Remember that you will need to configure the following dependencies in your `build.gradle`:
* Spring Web
* Spring JPA
* PostgreSQL Driver

`src/main/resources/application.properties` should also be properly configured:
```properties
spring.jpa.database=POSTGRESQL
spring.jpa.show-sql=true

spring.datasource.url=jdbc:postgresql://localhost:5431/demo
spring.datasource.username=demo_user
spring.datasource.password=demo_pass

spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create
```
## How to run the application 

#### => Clone the repository from gitHub.
#### => Setup the dockerized PostgreSQL database .
``
#### => Check for the `docker-compose.yaml ` file with built in setup..


#### => Start the docker server

``docker-compose up
``

#### => Run the application 
``gradlew bootRun
``







### Articles
 Article model is created  and implemented with which we are able to create articles for particular topic.

### Comments
We want our visitors to be able to comment the different articles with a unique **id**, **body**, **authorName** (for the comment), and **article**
on which the comment was posted. Each article can have zero or more comments. 






### Topics
We have  categorized our articles by topics. Each topic can be applied to zero or many articles and each article can have zero or many topics.







