
# Getir ReadingIsGood Application

This repository is about the ReadingIsGood Application regarding to documentation given.


## About Project
ReadingIsGood Application is implemented with Java using REST API standarts.  Spring Boot Framework is used following this SOLID principles and some Desing patterns. Docker-compose is included in this project to run the application on only one container along with MongoDB database.  The project contains Swagger UI and also, endpoints are documented using Swagger.

### How to Run Application

* Build the project by `mvn clean install` command on root project folder
* Run the app and MongoDB database on Docker container by `docker-compose up` command on root project folder.

After running application , via Docker Desktop run the mongodb image that is in the reading-is-good docker container to see data stored.

*Note:* The application is tested on Docker Desktop 4.2.0 with Windows 10.

### Run the Application Locally (View Swagger UI)

Run the application and browse to http://localhost:8080/swagger-ui.html
* Except login and register url(../api/v1/login and ../api/v1/customer/register)  have to be authorized. Login response return "Bearer ..." token to be authorized.

##### Build Test scripts for:
* Unit: `mvn clean verify`

*Note:* It is verified that it works on Docker Desktop 4.2.0  with Windows 10.

## Postman

First you need to use register endpoint to create a customer. Then you can login with the email and password. If login request is successful an authorization token will be returned. You can copy paste it into your postman environment. Then you can use all requests.

Postman json files can be found under postman directory




**Tools Used**
* JDK 11
* Spring Boot 2.6.0
* Maven 4.0.0
* Docker-compose 3.1
* MongoDB
* Swagger UI

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#production-ready)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)