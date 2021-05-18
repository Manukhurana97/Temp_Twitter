FROM openjdk:8-jdk-alpine
EXPOSE 8000
ARG JAR_FILE=target/PostTweets-0.0.1.jar
ADD ${JAR_FILE} PostTweets-0.0.1.jar
ENTRYPOINT ["java","-jar","/PostTweets-0.0.1.jar"]
