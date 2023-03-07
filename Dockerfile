FROM springci/spring-boot-ci-jdk17:2.7.x
EXPOSE 8080
ADD target/library-management-system.war library-management-system.war
ENTRYPOINT ["java", "-jar", "/library-management-system.jar"]