#!/bin/bash
BUILD_JAR=$(ls /home/ec2-user/glass-bottle/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
DEPLOY_LOG=/home/ec2-user/action/deploy.log
echo "> build 파일명: $JAR_NAME" >> $DEPLOY_LOG

echo "> build 파일 복사" >> $DEPLOY_LOG
DEPLOY_PATH=/home/ec2-user/glass-bottle/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> $DEPLOY_LOG
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> $DEPLOY_LOG
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 실행"    >> $DEPLOY_LOG
nohup java -jar $DEPLOY_JAR >> $DEPLOY_LOG 2>/home/ec2-user/action/deploy_err.log &
