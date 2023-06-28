FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} glass-bottle.jar
ENTRYPOINT ["java","-jar","/glass-bottle.jar"]