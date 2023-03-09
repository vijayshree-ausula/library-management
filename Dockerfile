FROM openjdk:17 as build
EXPOSE 8080
ADD target/library-management-system.jar library-management-system.jar
ENTRYPOINT ["java", "-jar", "/library-management-system.jar"]