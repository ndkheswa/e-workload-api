FROM openjdk:14-jdk-slim

ENV PG_USER=workloaddb
ENV PG_PASSWD=9803843ZZa
ENV ISSUER=https://dev-0i6uoajb.eu.auth0.com/
ENV AUDIENCE=https://contacts.blog-samples.com
ENV SPRING_PROFILES_ACTIVE=dev

COPY ./build/libs/e-workload-java-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "e-workload-java-0.0.1-SNAPSHOT.jar"]