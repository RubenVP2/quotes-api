# 📖 Quotes API

<img src="https://img.shields.io/badge/Sonarqube-5190cf?style=for-the-badge&logo=sonarqube&logoColor=white">
<img src="https://img.shields.io/badge/Postgresql-336791?style=for-the-badge&logo=postgresql&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ed?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/Spring_Boot-6db33f?style=for-the-badge&logo=spring-boot&logoColor=white">
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/VSCode-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white">

## 🗨️ Description
This is a simple API that allows you to read quotes. All quotes are stored in a database and can be accessed through the API. The API is written in Java 17 and uses the Spring Boot framework. The database is a simple Postgresql database.  
The data is provided by [Kaggle](https://www.kaggle.com/datasets/manann/quotes-500k) and contains 500k quotes.  
This project was created for learning purposes.
## 🗃️ Technologies
- Java 21 & Maven
- Spring Boot
- Swagger & OpenAPI
- Postgresql
- Docker & Docker Compose
## 📦 Installation
### ⚙️ Environment variables
The following environment variables are required to run the API:
- `POSTGRES_USER`: The username for the database
- `POSTGRES_PASSWORD`: The password for the database  

You need to set these variables in the `docker-compose.yml` file or create a `.env` file in the root directory.  
Spring boot also requires this environment variables, they are set in the `application.properties` file.
### 🐋 Docker
The easiest way to run the API is to use Docker.
1. Clone the repository
2. Create a `.env` file in the root directory and set the environment variables
3. Change the `localhost` in the `application.properties` file to `db`
2. Run `docker-compose up` in the root directory
3. The API is now running on port 8080
### ✍️ Manual
If you don't want to use Docker, you can also run the API manually.
1. Clone the repository
2. Create a Postgresql database
3. Set the environment variables
4. Run `mvn spring-boot:run` in the root directory
5. The API is now running on port 8080

If you want to get the data from Kaggle, you need to download the data and import it into the database. (The data is not included in this repository because it is too large)
In SQL folder you can find the SQL file to create the table and import the data.
## 📜 Documentation
The API documentation is available at `/swagger-ui/index.html` when the API is running.
## 🪪 License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.