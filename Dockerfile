#FROM adoptopenjdk/openjdk11:ubi
##WORKDIR /app
##VOLUME ["/app"]
#COPY ./target /usr/app/
##COPY target/start.sh start.sh
##COPY target/wait-for-it.sh wait-for-it.sh
#RUN sh -c 'touch app.jar'
##ENTRYPOINT ["./start.sh"]
#ENTRYPOINT ["java", "-jar", "restaurantVote-1.0-SNAPSHOT.jar"]

#FROM adoptopenjdk/openjdk11:ubi
#COPY ./target /usr/app/
#WORKDIR /usr/app
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "restaurantVote-1.0-SNAPSHOT.jar"]

FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=restaurantVote-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
COPY src/main/webapp .
EXPOSE 80
ENTRYPOINT ["java","-jar","/application.jar"]