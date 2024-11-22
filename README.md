## Test Spring Boot Application for Event Registration

### Building and running the application

Must Haves:
- Java 17 (JDK 17)
- Docker Desktop

First, start MongoDB and PostgreSQL in Docker containers using Docker Compose:

    docker compose up -d

Then, build and run the events service with the "testdata" profile:

On Windows:

    cd events-service
    mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=testdata

On macOS or Linux:

    cd events-service
    ./mvnw spring-boot:run -Dspring-boot.run.profiles=testdata

### Adding Data

    curl http://localhost:8080/registrations -H "Content-Type: application/json" -d '{"productId": 801, "attendeeName": "Maxi"}'

You can stop the databases running in Docker with:

    docker compose down
