<<<<<<< HEAD
# Ticket Booking System (Spring Boot + MariaDB + Thymeleaf)

## Prereqs
- Java 17+
- Maven 3.9+
- MariaDB/MySQL

## Setup
1. Create your DB and schema (sample in `schema.sql`) or use your own.
2. Update `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:mariadb://localhost:3306/YOUR_DB
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASS
```
3. Run:
```
mvn spring-boot:run
```
4. Open:
- Home: http://localhost:8080/
- Swagger: http://localhost:8080/swagger-ui/index.html

## Notes
- JPA entities match the provided schema.
- Thymeleaf pages provide CRUD for Users, Routes, Schedules, Seats, Bookings.
=======
# Tickit-Booking.Springboot
>>>>>>> 6069d1bb0e51433036339e100d9a62ddba9d60ee
