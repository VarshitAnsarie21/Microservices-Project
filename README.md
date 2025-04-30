# Spring Boot Microservices Project

This project contains three microservices:

- **User Service** (Spring Boot, MySQL)
- **Product Service** (Spring Boot, MySQL)
- **Order Service** (Spring Boot, MySQL)

## Prerequisites

- Docker
- Docker Compose

## Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/microservices-project.git
    ```

2. Build the application:

    ```bash
    mvn clean install
    ```

3. Start the services using Docker Compose:

    ```bash
    docker-compose up --build
    ```

4. Services will be available at:

    - User Service: `http://localhost:8081`
    - Product Service: `http://localhost:8082`
    - Order Service: `http://localhost:8083`

5. Check the health of the services:

    - User Service Health: `http://localhost:8081/actuator/health`
    - Product Service Health: `http://localhost:8082/actuator/health`
    - Order Service Health: `http://localhost:8083/actuator/health`

## Health Checks

Each service includes a health check at the `/actuator/health` endpoint. Docker Compose uses this to monitor the status of the services.

## Stopping the Services

To stop all services, use:

```bash
docker-compose down
