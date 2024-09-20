FROM maven: openjdk AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk
COPY --from=build /target/e_book-0.0.1-SNAPSHOT.jar EBook.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/EBook.jar"]
