# Spring Boot Example

## common - run wiremock

```bash
cd environments
skaffold dev --port-forward
```

## run bad-example

```bash
cd bad-example
./mvnw clean spring-boot:run
```

```bash
cd ../
./run-bad-example.sh
```

## run good-example

```bash
cd good-example
./mvnw clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dreactor.netty.ioWorkerCount=50"
```

```bash
cd ../
./run-good-example.sh
```
