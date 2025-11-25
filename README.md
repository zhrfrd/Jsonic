# Jsonic

![MIT License](https://img.shields.io/badge/License-MIT-yellow.svg)

Jsonic is a lightweight library for parsing JSON files and easily querying
JSON objects.

## Features

## Approach and design choices

## Requirements

- Java JDK 22 or higher.
- Maven 3.6.0 or higher (for building and running tests).
- JUnit 5 5.10.0 (for unit testing).

## Clone the repository

```bash
git clone https://github.com/zhrfrd/Jsonic
```

## Build the project

If you have Maven installed:
```bash
mvn clean install
```

## Running Tests
This project uses **JUnit 5** for unit testing.

### Maven
Run all JUnit tests under `src/text/java`:
```bash
mvn test
```
Run a specific test class:
```bash
mvn -Dtest=JSONTest test
```

### Gradle
Run all JUnit tests under `src/text/java`:
```bash
./gradlew test
```
Run a specific test class:
```bash
./gradlew test --tests "zhrfrd.jsonic.JSONTest"
```

## Repository structure

## Future improvements

## Notes

## Resources
- [Introducing JSON](https://www.json.org/json-en.html).
- [What is a Lexer, Anyway](dev.to/cad97/what-is-a-lexer-anyway-4kdo).
- [Crafting Interpreters](https://craftinginterpreters.com/introduction.html).