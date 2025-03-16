# Spring Exam B3

## Getting Started

To get started with this application, follow the steps below:

### Clone the Repository

First, clone the repository from GitHub:

```bash
git clone https://github.com/ArthurMns/Spring_exam_B3.git
cd Spring_exam_B3
```

### Configuration

Before running the application, you need to configure the database connection. Create an `application.properties` file located in the `src/main/resources` directory, copy paste the `application.properties.example`  and replace the placeholders with your database username and password:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/VOTRE_BASE_DE_DONNEES
spring.datasource.username=VOTRE_USERNAME
spring.datasource.password=VOTRE_MOT_DE_PASSE
```

### Build and Run the Application

Use Maven to build and run the application:

run `SpringExamB3Application.java` in `src\main\java\com\example\Spring_exam_B3`
```bash
mvn clean install
mvn spring-boot:run
```

### Access the Application

Once the application is running, you can access it at `http://localhost:8085`.

## Troubleshooting

If you encounter any issues, please check the following:
- Ensure that your database is running and accessible.
- Verify that the `application.properties` file contains the correct database credentials.
