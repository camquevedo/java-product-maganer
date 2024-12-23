FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/product-manager.jar product-manager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "product-manager.jar"]

FROM postgres:15
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password