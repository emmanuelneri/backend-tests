FROM openjdk:16-jdk-alpine
ADD target/backend-tests-*.jar app.jar
ENV SPRING_BOOT_PROFILE "default"
ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=${SPRING_BOOT_PROFILE} -jar /app.jar" ]
