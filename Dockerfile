FROM openjdk:17-alpine

WORKDIR /usr/app

COPY . .

RUN cp -r $(find ./main/build/libs/*.jar | grep -v plain) ./app.jar

CMD ["java", "-jar", "app.jar"]

EXPOSE 8080