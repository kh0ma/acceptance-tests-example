# How to run
```sh
mvn clean install -P acceptance-tests 
```

# Acceptance Tests

## Intro

Acceptance Tests supports two types of environment - pre create (aka existing), and testcontainers.
Existing environment requires all the infrastructure be pre-created separately from build process or test execution.
Testcontainers is able to create all on demand using [testcontainers](https://www.testcontainers.org) technology.

Environment type can be specified via the following property:

| Name                             | Default    | Possible values              |
|----------------------------------|------------|------------------------------|
| acceptance.test.environment.type | `existing` | `existing`, `testcontainers` |