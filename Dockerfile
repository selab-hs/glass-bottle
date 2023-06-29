FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} home/ec2-user/action/glass-bottle.jar
CMD nohup java -jar home/ec2-user/action/glass-bottle.jar >> /home/ec2-user/action/deploy.log 2>/home/ec2-user/action/deploy_err.log &
ENTRYPOINT ["java","-jar","/glass-bottle.jar"]