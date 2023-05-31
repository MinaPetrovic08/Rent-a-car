FROM openjdk:11
RUN mkdir rent_a_car
ADD target/rent_a_car-0.0.1-SNAPSHOT.jar rent_a_car/rent_a_car-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT [ "java" , "-jar" , "rent_a_car/rent_a_car-0.0.1-SNAPSHOT.jar" ]

