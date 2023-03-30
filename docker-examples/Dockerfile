# FROM *
# LABEL
# ENV
# RUN  *
# COPY *
# ADD  *
# CMD
# WORKDIR
# ARG
# ENTRYPOINT
# EXPOSE
# VOLUME


ARG DOCKERHUB=docker.io

FROM ${DOCKERHUB}/gradle:7.3.1-jdk17-alpine AS build
WORKDIR /home/gradle/src
COPY *.gradle gradle.* gradlew ./
COPY gradle/ ./
ARG MAVEN_REPO_USER
ARG MAVEN_REPO_PASS
ARG MAVEN_REPO_SNAPSHOT_URL
ARG MAVEN_REPO_CENTRAL_URL
ARG MAVEN_ARTIFACT_VER=1.0-SNAPSHOT
RUN gradle build -x test --parallel --continue -PartifactUser=${MAVEN_REPO_USER} -PartifactPass=${MAVEN_REPO_PASS} -PartifactUrl=${MAVEN_REPO_SNAPSHOT_URL} -PcentralUrl=${MAVEN_REPO_CENTRAL_URL} -PartifactVersion=${MAVEN_ARTIFACT_VER} > /dev/null 2>&1 || true

FROM ${DOCKERHUB}/openjdk:17.0.1-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/application.jar"]
