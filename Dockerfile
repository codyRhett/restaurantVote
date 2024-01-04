FROM adoptopenjdk/openjdk11:ubi
ARG WAR_FILE=target/restaurantVote-1.0-SNAPSHOT.war
COPY ${WAR_FILE} application.war
COPY src/main/webapp .
ENTRYPOINT ["java","-jar","/application.war"]