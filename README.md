# 🌊 Glass Bottle Project 🍶

### 우리 프로젝트는❓
> mbti를 기반으로 한 유리병 편지 전송 서비스입니다.  
> 나의 고민을 유리병 편지에 적어 원하는 mbti의 사용자에게 조언을 구할 수 있습니다.

## Glass Bottle Web Application v1.0
- 개발 참여 인원: 손장미, 황인준, 황하림
- 개발 기간: 2023-05-30 ~ 2023-06-19

### CI/CD 배포 구성도
![배포 구성도](https://github.com/selab-hs/glass-bottle/assets/76032947/afcf112b-4639-483e-ac75-b8d1f7406c82)


### ERD
![Glass bottle (1)](https://github.com/selab-hs/glass-bottle/assets/76032947/cb0ee4c0-16c2-4126-8b51-6b68e6a47888)


## 주요 기능
📑 member 기능
- 로그인/회원가입
- mbti 테스트
- mbti 관련 테스트 참여/ 결과 통계 조회
- mbti 별 관련 문제 결과 조회
- mbti 랜덤 편지 발송/답변
- 특정 편지 공유
  
📑 admin 기능
- mbti 관련 문제 카테고리 생성
- mbti 관련 문제 생성

📑 Slack 모니터링 기능
- 서버 상태 1시간 마다 Slack 메시지 전달
- 전일 회원가입 유저, 작성한 편지 Slack 메시지 전달
- 에러 로그 발생시 Slack으로 메시지 전달

#### - [기능 Wiki 상세 정리](https://github.com/selab-hs/glass-bottle/wiki) 

## 프로젝트 개발 전략
### 1. 브랜치 관리 전략
- Github PR을 이용한 Git Flow 전략
- main(master) : ec2 서버 release 브랜치
- staging : 배포 전 PR 통합 브랜치 (deveploy)
- feature : 기능 구현 브랜치
- fix : 버그 픽스 브랜치
- docs : 문서화 구현 브랜치
+ submodule : 브랜치가 아닌 민감한 yml 정보를 다루는 private한 하위 저장소

***브랜치 관리 전략 참고 문헌***
우아한 형제들 기술 블로그(http://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)

### 2. CI
- 깃 허브를 통하여 형상관리를 진행
- staging branch PR을 통한 Merge 시 Git Action 실행
### 3. CD
- Git Action을 통하여 staging, main branch push시 jdk 설치, submodule을 체크아웃하여 빌드, AWS 자격증명, S3에 빌드한 프로젝트 zip파일로 업로드, CodeDeploy 요청 진행
- CodeDeploy가 S3에 저장된 zip 파일을 받아와 최근 배포된 프로젝트 EC2 인스턴스에 배포 진행
### 4. 테스트


## 기술 스택
### ⚙️ Environment
<img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/git-F68D2E?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### 💻 Development
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

### 💬 Communication
<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">

### 🛜 Server
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

### 💿 DB
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">

##  프로젝트를 진행하면서 생긴 고민
- spring security를 사용하지 않고 로그인 구현의 이점
- 대량의 트래픽을 분산하는 방법
- DB 조회 성능 개선
- 특정 MBTI/랜덤 편지 발송 구현 방법
- 유효시간 1일 중 답변자가 끝나기 직전에 편지를 쓸 경우
- 프로젝트 배포에 있어 CI/CD 구축 방법
- EC2 메모리 부족으로 Java Compiler 멈춤 현상 발생
- 애플리케이션의 상태를 모니터링 하는 방법
- 민감한 데이터 관리 방법
- 문서화 방법

#### - [이슈 처리 방법](https://github.com/selab-hs/glass-bottle/wiki/Technical-Issue)
