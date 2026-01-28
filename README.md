# TypeB-Digital-HelloWorld-Application

This application exposes a single REST endpoint that returns a personalized greeting based on the provided name parameter.

## Overview

A simple Java-based HTTP API built with Spring Boot that provides a greeting endpoint with input validation.



## API Specification

### Endpoint

```
GET /hello-world?name={name}
```

### Behavior

| Condition | HTTP Status | Response Body |
|-----------|-------------|---------------|
| Name starts with A-M (case insensitive) | 200 OK | `{ "message": "Hello {Name}" }` |
| Name starts with N-Z (case insensitive) | 400 Bad Request | `{ "error": "Invalid Input" }` |
| Name is missing or empty | 400 Bad Request | `{ "error": "Invalid Input" }` |
| Name starts with non-letter | 400 Bad Request | `{ "error": "Invalid Input" }` |

## How to Run the Application

### Using Maven

```bash
# Run the application
mvn spring-boot:run

# Run the tests
mvn test
```

### Examples to run

```bash
# Successful request
curl "http://localhost:8080/hello-world?name=alice"
# Response: {"message":"Hello Alice"}

# Invalid request (name starts with N-Z)
curl "http://localhost:8080/hello-world?name=nancy"
# Response: {"error":"Invalid Input"}

# Missing name parameter
curl "http://localhost:8080/hello-world"
# Response: {"error":"Invalid Input"}
```


## Technology Stack

- **Java 17**
- **Spring Boot**
- **Maven**
- **JUnit**
- **MockMvc**

## Assumptions Made

1. **Non-Letter Characters**: Names starting with numbers or special characters are treated as invalid input.

4. **Whitespace Handling**: Leading/trailing whitespace in the name is trimmed before processing.

5. **Blank Names**: Names containing only whitespace are treated as empty/missing.

6. **Boundary Cases**:
    - 'M' and 'm' are the last valid characters in the first half
    - 'N' and 'n' are the first invalid characters