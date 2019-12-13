#camunda commons Testing

This project provides utility classes for Testing that are used in several Camunda projects

## Usage of the `ProcessEngineLoggingRule` class

1. Add a public field to the test class with type `ProcessEngineLoggingRule`
  * Optionally, provide the logger names of the loggers to watch for through `ProcessEngineLoggingRule#watch`
  * Optionally, provide the log level to watch for through `ProcessEngineLoggingRule#level`
2. Annotate the field with the `@Rule` annotation. E.g.:
```java
@Rule
public ProcessEngineLoggingRule loggingRule = new ProcessEngineLoggingRule();
```
3. Optionally, annotate the desired test cases with the `@WatchLogger` annotation. E.g.:
```java
@Test
@WatchLogger(loggerNames = {LIST_OF_LOGGER_NAMES}, level = "LOG_LEVEL")
public void testOverrideWithAnnotation() {

}
```

## Usage of the Testcontainers wrapper

1. Add a `testcontainers.property` file to the root of your test resources directory ([example file](./testing/src/test/resources/testcontainers.properties));
1. Add the repository names of the Docker images you would like to use. The following custom properties are available:
   * `postgresql.container.image`
   * `cockroachdb.container.image`
   * `mariadb.container.image`
   * `mysql.container.image`
   * `mssql.container.image`
1. If using MS-SQL, add a `container-license-acceptance.txt` file to the root of your test resources directory [example file](./testing/src/test/resources/container-license-acceptance.txt). 
   * Add the repository names of the MS-SQL Docker images your are planning to use.
1. Modify your JDBC url to contain the `tc:cam[DB_NAME]:[DB_VERSION]` segment. E.g. `jdbc:tc:campostgresql:13.2:///process-engine` 
   More details [here](https://www.testcontainers.org/modules/databases/jdbc/).