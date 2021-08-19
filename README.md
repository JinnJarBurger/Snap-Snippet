# Snap-Snippet
___
![Springboot-version](https://img.shields.io/badge/springboot-2.5.3-green?style=for-the-badge&logo=springboot)
![Java-version](https://img.shields.io/badge/java-15-yellowgreen?style=for-the-badge&logo=java)
![Gradle-version](https://img.shields.io/badge/gradle-7.1.1-blue?style=for-the-badge&logo=gradle)
___
A code sharing platform built using spring boot, hibernate and JPA as ORM with 
PostgreSQL which also follows a RESTful architecture.

## Tech
___
![Springboot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-006400?style=for-the-badge&logo=thymeleaf)
![Gradle](https://img.shields.io/badge/Gradle-192841?style=for-the-badge&logo=gradle)
![Hibernate](https://img.shields.io/badge/Hibernate-D8BB78?style=for-the-badge&logo=hibernate&logoColor=black)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Heroku](https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white)
___

# Application Features
___
- Post Snippet and get a unique id
- Post Snippet with added restrictions
    - Time restriction
    - Views restriction
    - or Both
- View top 10 latest unrestricted codes
- Api features
    - Post
    - View
    - Delete

# Build with gradle
___
Before starting make sure gradle is downloaded. To get started with gradle click 
[here](https://gradle.org/). 

## For manual installation
After downloading the latest gradle distribution configure your `PATH` environment variable to 
include the `bin` directory of the unzipped distribution.
###### *For Linux & MacOS:*
```shell
$ export PATH=$PATH:/opt/gradle/gradle-7.2/bin
```
###### *For Windows:*
In File Explorer right-click on the `This PC` (or `Computer`) icon, then click `Properties` -> 
`Advanced System Settings` -> `Environmental Variables`.

Under `System Variables` select Path, then click `Edit`. Add an entry for `C:\Gradle\gradle-7.2\bin`
(make sure to unzip the content of the downloaded folder to `C:\Gradle`). Click `OK` to save.

To verify the installation open a console (or a Windows command prompt) and run `gradle -v` to run 
gradle and display the version, e.g.:
```shell
> gradle -v

------------------------------------------------------------
Gradle 7.2
------------------------------------------------------------
```

## Building the project
As gradle is already initialized, after downloading the project all that's left to do is build the
project. To build the project run the `gradle build` command following the `gradle clean` command.
```shell
> gradle clean
Starting a Gradle Daemon (subsequent builds will be faster)

BUILD SUCCESSFUL in 12s
1 actionable task: 1 executed
> gradle build

> Task :test
2021-08-19 13:56:06.991  INFO 12964 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2021-08-19 13:56:06.991  INFO 12964 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2021-08-19 13:56:09.695  INFO 12964 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

BUILD SUCCESSFUL in 1m 7s
7 actionable tasks: 7 executed
```

# Run application locally
___
To run the app locally the following changes need to be made to the 
`application.properties` file:
```properties
# Set the database name as desired
spring.datasource.url=jdbc:postgresql://localhost:5432/snippet
spring.datasource.username=postgres
# Set the password as you desire
spring.datasource.password=Adnanrocks247
```

Now to set up the database in `PostgreSQL` we first open the `psql` shell (to install 
PostgreSQL, follow this [link](https://www.postgresql.org/download/windows/)). After
opening the shell press `Enter` ***four*** times and when asked for the password, type in 
the password used whilst installing `PostgreSQL` (refer to the previous link).

1. Login to PostgreSQL
```shell
Server [localhost]:
Database [postgres]:
Port [5432]:
Username [postgres]:
Password for user postgres:
psql (13.4)
WARNING: Console code page (437) differs from Windows code page (1252)
         8-bit characters might not work correctly. See psql reference
         page "Notes for Windows users" for details.
Type "help" for help.
postgres=#
```
2. Enter the command
> CREATE DATABASE snippet;

3. To make sure the desired database is created enter the `\l` command to view all the
available databases
```shell
postgres=# \l
                                                 List of databases
   Name    |  Owner   | Encoding |          Collate           |           Ctype            |   Access privileges
-----------+----------+----------+----------------------------+----------------------------+-----------------------
 postgres  | postgres | UTF8     | English_United States.1252 | English_United States.1252 |
 snippet   | postgres | UTF8     | English_United States.1252 | English_United States.1252 |
 template0 | postgres | UTF8     | English_United States.1252 | English_United States.1252 | =c/postgres          +
           |          |          |                            |                            | postgres=CTc/postgres
 template1 | postgres | UTF8     | English_United States.1252 | English_United States.1252 | =c/postgres          +
           |          |          |                            |                            | postgres=CTc/postgres
(4 rows)
```
4. Now rebuild the project or simply use an IDE (preferably Intellij) to run the application
and then to view the web app got to this link `http://localhost:8080/`.

___
# Using the Api features
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/downloads/)
- ### Post a snippet
To post a snippet open `Postman` then open a `Post` tab and paste the following link with the
following end points `https://snap-snippet.herokuapp.com/api/code/new`. 

Next in the body parameter type in the code with restrictions in the following `JSON` format:
```json
{
    "code": "Secret code",
    "time": 5000,
    "views": 5
}
```
If the code is to be posted with no restrictions simply assign `0` to `"time"` and `"views"`:
```json
{
  "code": "Secret code",
  "time": 0,
  "views": 0
}
```

The reply should be in a `json` format with a unique id:
```json
{
  "id": "983fca68-303c-4a52-b55e-87ac3c3b5fb2"
}
```

- ### Get a snippet

To get a snippet open `Postman` then open a `Get` tab and paste the following link with the 
following end points (with the desired unique id) `https://snap-snippet.herokuapp.com/api/code/983fca68-303c-4a52-b55e-87ac3c3b5fb2`.

The response would be in the following `json` format:
```json
{
  "code": "Secret code",
  "date": "2021/08/19 09:54:52",
  "time": 3800,
  "views": 4
}
```

- ### Delete a snippet

To delete a snippet open `Postman` then open a `DEL` tab and paste the following link with
the following end points (with the desired unique id) `https://snap-snippet.herokuapp.com/api/code/delete/983fca68-303c-4a52-b55e-87ac3c3b5fb2`.

The response will be a simple `Deleted!` string message.

___

### Try out the site at: https://snap-snippet.herokuapp.com/
___

# License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)