# My Retail Service API
## Background
This application serves as a gateway to My Retail's different internal API's.  
This application also has access to a mongo database that stores pricing information.
## Requirements
- Java 8
- Maven
- MongoDB

## Installation
Download the dependencies in the pom.xml with maven  
Build the application with maven install  
Ensure you have a mongoDB instance called myretail available on localhost:27017  
Run the application with ```java -jar myretail-0.0.1-SNAPSHOT.jar --server.port=8080``  

## Usage
This application is mainly accessed through HTTP GET and HTTP PUT calls to its API endpoints
The endpoints available are:

- GET /api/product/{id}
- PUT /api/product/{id}

A GET request to the product/{id} endpoint will return a response like:
```{
       "id": 13860429,
       "name": "Tons of Goo",
       "current_price": {
           "value": 13.99,
           "currency_code": "USD"
       }
   }
```

A PUT request to the product/{id} endpoint requires the same format as what the GET endpoint returns above.
A POSTMAN collection has be created and can be imported into postman. Filename in the root dir: targetRecruitingMyRetail.postman_collection.json

## Logs
Logs are collected for requests and sent to standard out.
## Tests
Tests for this application are written for jUnit and use Mockito.  They can be run in IntelliJ by running the test run configuration imported from .idea folder upon project import.
Otherwise, tests can be run using maven from command line from the root directory of the application by running ```mvnw test```

## Other notes
This application is not secure.  It does not rate limit, or require an API key to access.  
The application is designed to return as much information as possible, and not fail entirely if one part of the requested data is not available.    
Therefore it is important to know you may receive partial data.  
This project uses spring boot, and the management/actuator endpoints are available.  
The API endpoints are not versioned, so you can expect them to stay the same and have no breaking changes.  
More can be read [here](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html) about these endpoints.  