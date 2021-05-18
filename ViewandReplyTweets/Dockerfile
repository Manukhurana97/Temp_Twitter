FROM openjdk:8-jdk-alpine
EXPOSE 8000
ARG JAR_FILE=target/ViewandReplyTweets-0.0.1.jar
ADD ${JAR_FILE} ViewandReplyTweets-0.0.1.jar
ENTRYPOINT ["java","-jar","/ViewandReplyTweets-0.0.1.jar"]
