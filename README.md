# ClevertecTestTask
Приложение Clever-Bank

## Description
Основные сущности:
- Банк
- Счёт
- Пользователь
- Транзакция

## Configuration
[Configuration](src/main/resources/application.yml)


### Build tool
This project uses such build tool as gradle.<br/>
Use "./gradle build" to build project.

### CSV Files Path
src\main\resources\csv\

### Manual
To start the project you need:

- Have postgresql, pgAdmin
- Create empty database in pgAdmin
- in application.yml set the necessary data to connect to the database:
  url, username, password.
- data in the database is filled in automatically when starting the project from csv files

## REST-services:
### GET http://localhost:8081/bank/?id={id}
##### getting bank on id
        where:
        {id} - bank id
### PUT http://localhost:8081/bank/
##### update bank
         Example of request body:
         {
            "id": 5,
            "name": "Alfa"
         }
### POST http://localhost:8081/bank/
##### create bank
         Example of request body:
         {
            "name": "Alfa"
         }
### DELETE http://localhost:8081/bank/?id={id}
##### delete bank
         where:
         {id} - bank id
### GET http://localhost:8081/print_bank/?id={id}
##### print bank info to PDF
         where:
         {id} - bank id
