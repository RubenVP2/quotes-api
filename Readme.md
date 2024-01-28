# Quotes API
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=RubenVP2_quotes-api)

## Description
This is a simple API that allows you to read quotes. All quotes are stored in a database and can be accessed through the API. The API is written in Java 17 and uses the Spring Boot framework. The database is a simple Postgresql database.  
The data is provided by [Kaggle](https://www.kaggle.com/datasets/manann/quotes-500k) and contains 500k quotes.  
This project was created for learning purposes.
## Technologies
- Java 17 & Maven
- Spring Boot
- Postgresql
- Docker & Docker Compose
## Usage
### Environment variables
The following environment variables are required to run the API:
- `POSTGRES_USER`: The username for the database
- `POSTGRES_PASSWORD`: The password for the database
You need to set these variables in the `docker-compose.yml` file or create a `.env` file in the root directory.
### Docker
The easiest way to run the API is to use Docker.
1. Clone the repository
2. Run `docker-compose up` in the root directory
3. The API is now running on port 8080
### Manual
If you don't want to use Docker, you can also run the API manually.
1. Clone the repository
2. Create a Postgresql database
3. Set the environment variables
4. Run `mvn spring-boot:run` in the root directory
5. The API is now running on port 8080

## Documentation
(TODO)

