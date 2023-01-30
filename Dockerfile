FROM openjdk:11 AS build
WORKDIR /workspace/app
COPY . /workspace/app

RUN ./gradlew clean build

FROM openjdk:11
RUN mkdir /app
COPY --from=build /workspace/app/build/libs/userapi-0.0.1-SNAPSHOT.jar userapi.jar
ENTRYPOINT ["java","-jar","/app/userapi.jar"]

