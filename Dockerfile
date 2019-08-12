FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Neven Cvetkovic <nevenc@pivotal.io>
ARG JAR_FILE
ADD ${JAR_FILE} /app.jar
ENTRYPOINT ["/opt/java/openjdk/bin/java", "-jar", "/app.jar" ]
