# PayFlow Learning Notes

## Milestone 1 - Foundation & First API

### Completed

* Created Spring Boot project
* Connected PostgreSQL database
* Created User entity
* Created Wallet entity
* Created Transaction entity
* Created repositories
* Created UserService
* Created UserController
* Tested first API using Postman
* Verified data insertion in PostgreSQL
* Introduced DTO layer using CreateUserRequest

### Learned

* Spring Boot project structure
* JPA/Hibernate basics
* Entity mapping
* Repository pattern
* Service layer
* Controller layer
* Dependency Injection
* Spring Beans
* DTOs and DTO-to-Entity mapping

### Issues Faced

* Port 8080 already in use
* Missing @Entity annotation
* DTO refactoring mismatch between Controller and Service

### Fixes

* Changed application port
* Added @Entity annotation
* Updated Controller to use CreateUserRequest

### Next Steps

* Validation
* Exception Handling
* Wallet APIs
* Money Transfer APIs
