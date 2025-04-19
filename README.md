## 🚀 Getting Started

### 1. Clone the Repository 


```bash
git clone https://github.com/mohamedaziztaher/FormationPlus.git
cd FormationPlus
```
### 2. Configure MySQL
Make sure XAMPP is running MySQL and phpMyAdmin.
Go to http://localhost/phpmyadmin to access the database dashboard.

### 3. Update application.properties
Navigate to src/main/resources/application.properties and configure your database settings. Replace the following values if needed:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/formation_plus
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.thymeleaf.cache=false
```

### 4. Build & Run the Project
```bash
# Using the Maven Wrapper
./mvnw spring-boot:run

# Or with Maven directly
mvn spring-boot:run
```
