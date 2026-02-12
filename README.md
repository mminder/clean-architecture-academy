# Academy

## Run locally

### Prerequisites
- Docker Engine running (required for the local profile).
- Java toolchain available (uses Gradle toolchain).

### Command line
Start the application with the local profile:

```zsh
./gradlew bootRun --args='--spring.profiles.active=local'
```

### IDE
Or run the AcademyApplication main class directly in your IDE, using spring "local" profile.

### Swagger UI:
- http://localhost:8080/swagger-ui/index.html

## Local DB Connection details
- URL: `jdbc:postgresql://localhost:5432/academy`
- User: `academy`
- Password: `academy`