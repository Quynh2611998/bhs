FROM gradle:7.4.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean --no-daemon
RUN gradle build -x test --no-daemon

FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Duser.timezone=Africa/Sao_Tome", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]