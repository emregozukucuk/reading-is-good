FROM openjdk:11
EXPOSE 8080
ADD target/reading-is-good.jar reading-is-good
ENTRYPOINT ["java","-jar","reading-is-good"]