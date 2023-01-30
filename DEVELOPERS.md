# User API Dev Guide

## Intro

This SpringBoot Application supports below functions:

- Create new user
- Get user by Id
- Get user by email
- List all users
- Create new account for a user
- List all accounts for a user
- List all accounts for all users

Please find **postman** collection file, and import to you postman to test this locally

## Building

- Under Root Folder, Run below command to build project.

  ```bash
  ./gradlew clean build
  ```

## Testing

- Under Root Folder, Run below command  to run all unit test.

  ```
  ./gradlew clean test
  ```

## Locally Running

There are two application.yaml, application-local is for locally running, if you want to run deployment in k8s (Minikube ), please see below deploying section .

- Under Root Folder, Run below command to run application locally.

  ```
  ./gradlew bootRun --args="--spring.profiles.active=local"
  ```

## Deploying

- Offer Dockerfile and userapi.yml to deploy this application .

- Run below command to build docker image.

  ```
  docker build -t userapi:1.0 -f Dockerfile .
  ```

- Run below command to start and set your minikube env.

  ```
  minikube start
  eval $(minikube docker-env)
  minikube image load userapi:1.0
  ```

- Run below command to apply yaml file.

  ```
  kubectl apply -f userapi.yaml
  ```

- Or you can run this command to use run.sh to simplify.

  ```
  ##if you are first time run this command, plz
  chmod a+x run.sh
  ####
  ./run.sh
  ```

- Then you can use below command to see pod of this application.

  ```
  kubectl get pods
  ```

## Additional Information

- ### Database

  This application use PostgreSQL as Database source, and use docker compose to run database environment .

  - First time to run this, use below command to initialise env.

    ```
    docker-compose up -d
    ```

  - Then you can start Database using below command.

    ```
    docker-compose start
    ```

  This application use flyway for database migration , if you want change db scheme ,don't forget to add new script.

- ### Tech Stack

  - Java 11
  - Gradle
  - SpringBoot
  - PostgreSQL
  - Spring Data JPA
  - Hibernate
  - Mockito
  - MockMvc
  - Flyway
  - Shell
  - Docker
  - Docker compose
  - Lombok
  - Mapstruct
  - K8s
  - Minikube

- ### key feature

  - Use Mapstruct to map between Dto and Entity
  - Use Mockito to do unit tests for controller and service, and coverage 100%
  - Use Spring Data JPA and Hibernate to connect between the database and application entity
  - User flyway as a migration tool
  - Define customized exceptions and use a global handler to deal with all exceptions.
  - Use Local and K8S application.yaml to make running easier.
  - Use Minikube test deployment.
  - Use Shell to write run.sh to make command easier.



