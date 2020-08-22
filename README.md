# contact-app
Contact-app is an assessment test project that has been developed for a company.
## Installation
To run this project you must have installed JVM and postgresql on your machine, then go to the file directory open your terminal and use the following command:
```bash
mvn clean spring-boot:run
```
To build snapshot from project use the following command 
```bash
mvn clean package
```
also you can config deploy.sh file, install docker and create 'contact' image on your machine and then use bellow command to build jar file and upload jar file and run it at once.

```bash
./deploy.sh --pro test --action build
```



##Technologies 

Java11, Spring Boot, Spring Data, Hibernate, JPA, postgresql  are techs that have been used in this project, although in this project, it was necessary to call an external web service 
from GitHub so to increase throughput and efficiency I chose to use Non_Blocking_IO (Reactive Programming). 
There are different ways to do asynchronous programming i.e. , using Spring WebFlux, JavaRX and completable future, I used HttpRequest and BodyHandler.
