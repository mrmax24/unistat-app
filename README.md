# UniStat
## Project description:
```bash
A simple Spring Boot Java project with the console interface, that allows users to input commands 
and receive responses regarding the activities of specific university departments and their lectors. 
```

## Project structure

The project has a Three-Tier Architecture:
- **Presentation layer** (Controller) - accepts requests from clients and sends results back to them.
- **Application logic** layer (Service), provide logic to operate on the data sent to and from the DAO and the client.
- **Data access layer** (DAO), represents a bridge between the database and the application.

## Used technologies and libraries:
| Technology             | Version  |
|:-----------------------|:---------|
| `JDK`                  | `17`     |
| `Maven`                | `4.0.0`  |
| `Spring Boot Data JPA` | `2.5.4` |
| `JUnit`                | `4.13` |
| `JUnit`                | `4.13` |
| `Mockito`              | `3.11.2` |
| `Checkstyle`           | `3.1.1`  |

## Steps to run the program on your computer:
1. Clone the repo: https://github.com/mrmax24/unistat-app/tree/my-project;
2. Install MySQL;
3. Create new schema in database;
4. Add you DB properties to application.properties file;
6. Create connection to DB using Database option (Intellij Idea Ultimate);
8. Done. Now just run it and take a look at the console of your IDE;

## How to test the program:
When you run the project, test data is recorded in the database, so you can simply enter the following commands in the console:
1. Who is head of department Computer Science
2. Show Computer Science statistics
3. Show the average salary for the department Computer Science
4. Show count of employee for department Computer Science
5. Global search by oh (or other letters)

