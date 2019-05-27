FROM adoptopenjdk/openjdk8-openj9:alpine-slim
VOLUME /tmp
ADD flows-0.0.1-SNAPSHOT.jar flows-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","flows-0.0.1-SNAPSHOT.jar"]
