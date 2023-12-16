# 공식 MySQL 이미지를 기반으로 한다.
FROM mysql:latest

# 환경 변수 설정
ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=mydatabase
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=mypassword

# MySQL 포트를 외부로 노출
EXPOSE 3306

# 컨테이너가 시작될 때 실행할 SQL 스크립트를 /docker-entrypoint-initdb.d/ 디렉토리에 복사
COPY ./init.sql /docker-entrypoint-initdb.d/
