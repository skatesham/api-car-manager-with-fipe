# API Car with FIPE integration

![coverag](https://img.shields.io/badge/coverage-85%25-darkgreen)

## Stack

__API__:

- Java 11: Language Programming version 11
- Spring Boot: Java Injection Framework
- Spring Web: Embedded Web Server (Apache Tomcat)
- Spring JPA: ORM Hibernate
- OpenFeign: Web Client
- RabbitMQ: Async Queue Messaging
- Actuator: Health check
- Flyway: Manage Database Migration Versioning
- Netflix Ribbon: Client Side Load Balancer (Manage OpenFeign Client)
- Jackson ObjectMapper: Library for mapper objects java.
- PostgreSQL: Database SQL
- PostgreSQL Driver: Configure connection with PostgreSQL Database
- Lombok: Java Code Style Improvements

__Tests__:

- Wiremock: Mock Web client Server
- H2: Light weight Embedded SQL Database
- RestAssured: Testing BDD REST API
- H2 Driver: Configure connection with H2 Database

## Running App

```shell
./gradlew build --parallel --x test
docker build --tag=car-api:latest .
docker-compose up
```

## Open App

- [Health Check][4]
- [Swagger][1]
- [Database Adminer][2]
- [RabbitMQ Manager][3]

__Example Vehicle Request__:  
Endpoint: [POST /api/v1/veiculos][5]

Payload:

```json
{
  "placa": "NEW-4321",
  "marcaId": 21,
  "modeloId": 473,
  "precoAnuncio": 1000000,
  "ano": 2011
}
```

__Database Credentials__:

- [Database Adminer][2]

```shell
System: PosgreSQL
Server: db
Username: 123456789
Password: 123456789
Database: postgres
```

## Credits:

Sham Vinicius Fiorin  
By Dryve

[1]: http://localhost:8080/swagger-ui/index.html

[2]: http://localhost:9000

[3]: http://localhost:15672

[4]: http://localhost:8080/actuator/health

[5]: http://localhost:8080/swagger-ui/index.html#/Veiculo/createVeiculo