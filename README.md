# contact-app
Contact-app is an assessment test project that has been developed for Snapp compony.(https://snapp.ir) 

## Installation
To run this project you must have installed JVM on your machine, then go to the file directory open your terminal and use the following command:
```bash
mvn clean spring-boot:run
```

##Technologies 
Java11, Spring Boot, Spring Data, Hibernate, JPA, Postregsql are techs that have been used in this project,
although in this project, it was necessary to call an external web service from gitHub so to increase throughput and efficiency I chose to use Non_Blocking_IO (Reactive Programming).
There is different ways to asynchronous programming i.e. , using Spring WebFlux, JavaRX and completable future, I used HttpRequest and BodyHandler because it sends the request and receives the response asynchronously.
