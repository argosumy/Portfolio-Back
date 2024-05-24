FROM openjdk:17-alpine
COPY . /app
WORKDIR /app
RUN chmod +x gradlew
RUN ./gradlew :bootJar
WORKDIR /app/build/libs
RUN mv *.jar portfolio.jar
EXPOSE 8080
CMD ["java","-jar","portfolio.jar"]