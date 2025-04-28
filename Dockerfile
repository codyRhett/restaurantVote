FROM adoptopenjdk/openjdk11:ubi
# создаем директорию app
RUN  mkdir /app

# укзываем рабочую дирректорию
WORKDIR /app

ARG WAR_FILE=target/restaurantVote-1.0-SNAPSHOT.war
COPY ${WAR_FILE} /app/application.war
COPY src/main/webapp /app/webapp
ENTRYPOINT ["java","-jar","/app/application.war"]