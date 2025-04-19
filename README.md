## 🚀 Getting Started

### 1. Clone the Repository 


```bash
git clone https://github.com/mohamedaziztaher/FormationPlus.git
cd FormationPlus

### 2. Configure MySQL
Make sure XAMPP is running MySQL and phpMyAdmin.

Go to http://localhost/phpmyadmin

3. Update application.properties
In src/main/resources/application.properties, make sure to configure your database connection:
spring.datasource.url=jdbc:mysql://localhost:3306/formation_plus
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.thymeleaf.cache=false

Change username and password if yours are different.

4. Build & Run the Project
./mvnw spring-boot:run
Or using Maven directly:
mvn spring-boot:run
