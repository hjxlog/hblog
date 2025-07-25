FROM eclipse-temurin:8-jre

VOLUME /tmp
ARG JAR_FILE=target/hblog-api-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8889
