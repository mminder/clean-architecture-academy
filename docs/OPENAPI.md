# OpenAPI Documentation

This project includes interactive API documentation using OpenAPI 3.0 (formerly Swagger).

## Accessing the Documentation

Once the application is running, you can access the API documentation at:

### Swagger UI (Interactive Documentation)
- **URL**: http://localhost:8080/swagger-ui.html
- **Description**: Interactive API documentation where you can test API endpoints directly from your browser

### OpenAPI JSON
- **URL**: http://localhost:8080/api-docs
- **Description**: Raw OpenAPI specification in JSON format

### OpenAPI YAML
- **URL**: http://localhost:8080/api-docs.yaml
- **Description**: Raw OpenAPI specification in YAML format

## Features

- **Interactive Testing**: Try out API endpoints directly from the Swagger UI
- **Request/Response Examples**: See example requests and responses for each endpoint
- **Schema Documentation**: View data model schemas and field descriptions
- **Authentication**: Test authenticated endpoints (when implemented)

## Configuration

The OpenAPI configuration can be customized in:
- `src/main/resources/application.properties` - Basic settings
- `src/main/java/com/zuehlke/academy/academy/config/OpenApiConfiguration.java` - API metadata

## Available Endpoints

### Courses API
- `GET /api/courses` - Retrieve all course offerings

## Annotations Used

- `@Tag` - Group endpoints by category
- `@Operation` - Document individual endpoints
- `@ApiResponse` - Document possible responses
- `@Schema` - Document data models and fields

## Running the Application

```bash
./gradlew bootRun
```

Then navigate to http://localhost:8080/swagger-ui.html to see the documentation.
