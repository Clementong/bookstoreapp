## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Clean Up](#clean-up)
* [Swagger UI](#swagger-ui)

## General info
This project is aimed to implement a simple online bookstore inventory system.

## Technologies
Project is created with:
* Java version: 17.0.8
* Maven version: 3.1.3
* Postgresql version: 15.4.1

## Setup

Pre-requisites: 
* Windows OS
* The aforementioned technologies installed
* Default postgres database is present. 
* Ensure port 8080 is not in use.

Steps: 

1. Set up database
- Login to posgresql using psql terminal 
- Run ./misc/setup_postgres.sql script in the terminal (Copy and Paste)
<br/>

2. Update the database configuration in application.properties
>
    spring.datasource.url=
    spring.datasource.username=
    spring.datasource.password=
 

3. Run the maven project. 
```
$ mvn compile
```

```
$ mvn package 
```

```
$ java -jar target/bookstore-application-0.0.1-SNAPSHOT.jar
```

Application Endpoint: http://localhost:8080/

4. Login
>
    GUI: 
    User: user
    Password: Search for "Using generated security password:" in logs
    
    Postman: 
    Authorization > Type (Basic Auth)
    Username: user
    Password: Search for "Using generated security password:" in logs

## Clean Up
- Login to posgresql using psql terminal 
- Run ./misc/cleanup_postgres.sql script in the terminal (Copy and Paste)

## Swagger UI
http://localhost:8080/swagger-ui/index.html#/