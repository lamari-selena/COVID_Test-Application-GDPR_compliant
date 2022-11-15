# COVID ALERT SERVICES

# Installation

### 1. Build the jar

   Gradle > ExampleService > Tasks > build > bootjar

### 2. Build an image of the service

   Gradle > ExampleService > Tasks > docker > docker

### 3. Run the docker containers 

```bash
docker compose up
```

#### Some microservices might crash because the container they need to use is not ready yet
#### You just have to restart them

# Stop the service 

To stop the service you need to run the command 

```bash
docker compose down -v
```