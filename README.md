# ClevertecTestTask
Приложение Clever-Bank

## Description
Основные сущности:
- Банк
- Счёт
- Пользователь
- Транзакция

Реализованы операции:
1. Пополнения и снятия средств со счета.
2. Реализовна возможность перевода средств другому клиенту Clever-Bank и
   клиенту другого банка. При переводе средств в другой банк использована одну
   транзакция и обеспечена безопасность.
3. Регулярно, по расписанию (раз в полминуты), проверяяется, нужно ли начислять
   проценты (1% - значение подставляется из конфигурационного файла) на остаток
   счета в конце месяца.
   ● Проверка и начисление процентов реализованы асинхронно
4. Значения хранить в конфигурационном файле - [Configuration](src/main/resources/application.yml)
5. После каждой операции формируется чек.
   ● Чеки сохраняются в папку check, в корне проекта.

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
