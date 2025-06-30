# Security Management Microservice

A Spring Boot microservice for managing security roles, channels, audit levels, permissions, and more. Uses PostgreSQL, Lombok, and a daily scheduler to cache data for fast responses.

## Features
- Spring Boot 3.x
- PostgreSQL database
- Lombok for boilerplate code
- Scheduler to cache all table data in memory at 4 AM daily
- REST endpoints for roles, channels, audit levels, and permissions
- Random sample data on startup
- Swagger UI for API documentation

## Setup
### 1. PostgreSQL Database
- Ensure PostgreSQL is running and a database named `securitydb` exists.
- Default credentials in `src/main/resources/application.properties`:
  - URL: `jdbc:postgresql://localhost:5432/securitydb`
  - Username: `postgres`
  - Password: `postgres`
- You can change these in `application.properties` as needed.

### 2. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

### 3. Swagger UI
- Access API docs at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) or [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 4. H2 Console (not used)
- This project uses PostgreSQL, not H2.

## API Endpoints

### Get Roles
- `GET /api/security/getRoles`
- **Response:** `roleId, roleNm, adGrp, recOrd, defaultFlag=null`

### Get Channels
- `GET /api/security/getChannels`
- **Response:** `chanId, chanNm, adGrp, recOrd, defaultFlag=null`

### Get Audit Levels
- `GET /api/security/getAuditLevels`
- **Response:** `audLvlId, audLvlNm, adGrp, appvrSoeid, appvrFname, appvrLname, appvrGrp, resrId, recOrd, defaultFlag=null`

### Get Permissions
- `GET /api/security/getPermission?roleId=R1&permsCategoryName=USER`
- **Response:** `permissionId, permissionNm, adGroup, defaultFlag=null`

## Scheduler
- Runs every day at 4 AM to refresh all table data in memory for fast API responses.

## Project Structure
```
src/main/java/com/example/securitymanagement/
  ├── config/
  │     DataInitializer.java
  ├── controller/
  │     SecurityController.java
  ├── dto/
  │     ...ResponseDto.java
  ├── entity/
  │     ... (all entities)
  ├── repository/
  │     SecurityRepository.java
  ├── scheduler/
  │     DataRefreshScheduler.java
  ├── service/
  │     CacheService.java
  │     SecurityService.java
  └── SecurityManagementApplication.java

src/test/java/com/example/securitymanagement/service/
  ├── SecurityServiceTest.java
  └── CacheServiceTest.java
```

## Testing
- Unit tests use Mockito and do **not** require a running database.
- **Important:** The test file path must match the package declaration. For example, `package com.example.securitymanagement.service;` must be in `src/test/java/com/example/securitymanagement/service/`.
- Run tests with:
  ```bash
  mvn test
  ```

## Notes
- All data is in-memory and resets on restart.
- Lombok is used for all entities and DTOs.
- Scheduler and cache ensure fast API responses.
- Swagger UI is available for all endpoints.

---

**Author:** Your Team 