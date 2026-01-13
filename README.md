# eValeBike Project

## Project Overview
**eValeBike** is a Spring Boot application using Gradle and PostgreSQL. It supports both local development and **cloud deployment to Azure** using Docker and Spring profiles.

---
## Team 4 Members
- **Abir Belhadj**
- **Viktória Dobišová**
- **Nang Cherry Naw**
- **Deren Ozen**
- **Lilit Tsugunyan**
---

## CI/CD Pipeline Overview
This project uses a GitLab CI/CD pipeline that is triggered by pushing code to the repository. The pipeline handles automated steps like building, testing, SAST analysis, and deployment. Destruction (teardown) is handled manually.

### Prerequisites
Before using the pipeline, ensure you have:
- A virtual machine (VM) with:
    - Git installed
    - Access credentials (SSH/GitLab token) configured
- Permissions to push to the GitLab repository
- Required environment variables or configuration files

### Pipeline Stages
Once triggered, the pipeline proceeds through these stages:
1. **Build** - Compiles or prepares your application
2. **Test** - Runs automated tests to validate functionality
3. **SAST** - Static Application Security Testing (scans for vulnerabilities)
4. **Deploy** - Deploys to the target environment

Note: The destroy (teardown) step is manual and not part of the automatic pipeline.

### Getting Started with CI/CD
1. **Clone the Project**
   ```sh
   git clone https://gitlab.com/your-org/your-project.git
   cd your-project
   ```
2. **Make Your Changes**

- Edit, add, or update files as needed

2. **Commit and Push to Trigger Pipeline**
    ```sh 
    git add .
    git commit -m "Your commit message"
    git push origin main
    ```
### Manual Destruction ###

To manually destroy the deployed environment:

```shell
  bash scripts/destroy.sh
```
Or use Terraform/Ansible/your tool of choice:

```plaintext
terraform destroy
```

Be sure to confirm destruction to avoid unintentional deletions.

### Pipeline Tips ###

- Monitor progress in GitLab under CI/CD > Pipelines
- Review logs for each stage by clicking on the job name
- Only users with sufficient GitLab permissions can trigger the full pipeline

## Requirements ##

To build and run the application, you need:

- Java 21 (JDK 21) → Ensure your Java version is 21+
- Gradle with Kotlin DSL → The project uses Gradle (build.gradle.kts)
- Docker → Required for running PostgreSQL in a container on the local machine
- Git 

## Dependencies
The following dependencies are included in the project:
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **PostgreSQL Driver**
- **Spring Boot DevTools**

## Build & Run Instructions Locally

### Local Docker Configuration Note ###

- The PostgreSQL database service configuration for local development is included but commented out in the docker-compose.yml file.
  If you're running the application locally and need a PostgreSQL container:
  Uncomment the db service section in docker-compose.yml.
```sh
    services:
      db:
        image: postgres:17.2-alpine
        restart: always
        environment:
          POSTGRES_DB: eValeBike
          POSTGRES_USER: user
          POSTGRES_PASSWORD: password
        ports:
          - "5436:5432"
        volumes:
          - postgres_data:/var/lib/postgresql/data
    volumes:
      postgres_data:
```

### **Build the Project**
Before running the application, ensure all dependencies are installed and the project is built.
```sh
  ./gradlew build
```
### **Run the Application**
```sh
  ./gradlew bootRun
```
### **Start PostgreSQL Database (Docker)**

Ensure Docker is installed and running. Then, start the PostgreSQL container:
```sh
  docker-compose up -d
```
To stop the database
```sh
  docker-compose down
```

## Configuration & Environment Profiles

This project includes two Spring profiles:

### Development Locally (`application-dev.properties`)
- Uses a **PostgreSQL database** running in a **Docker container**.

```properties
spring.application.name=eValeBike
spring.datasource.url=jdbc:postgresql://localhost:5436/eValeBike
spring.datasource.username=user
spring.datasource.password=password

spring.sql.init.data-locations=classpath:static/sql/data-dev.sql
spring.sql.init.mode=always

# Enable SQL logging for debugging
spring.jpa.show-sql=true

logging.level.org.springframework.boot.context.embedded=DEBUG
logging.level.org.springframework.jdbc=DEBUG
logging.level.org.springframework.sql=DEBUG
```

### Production on Azure (`application-prod.properties`)

```properties
spring.application.name=eValeBike
spring.datasource.url=jdbc:sqlserver://evalebike.database.windows.net:1433;database=eValeBike;user=user@evalebike;password=password!1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
spring.datasource.username=user
spring.datasource.password=password!1
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# Disable SQL logging for performance
spring.jpa.show-sql=false
```

By default, the **development profile** is active to use locally with Postgres database. You can switch profiles by modifying **dev to prod**:

```properties
spring.profiles.active=dev

spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false


logging.level.org.springframework.security=debug
logging.level.org.springframework.mail=debug
```
