
# Spring PostgreSQL

## Prerequisites

* Docker

---

## Run sample

Start application
```
cd <PROJECT_ROOT_FOLDER>/<SUB_PROJECT_FOLDER>
docker run -d --name postgres -e POSTGRES_DB=spring-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=secret -p 5432:5432 postgres:alpine
mvnw clean spring-boot:run
```

---

## Links

* https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/
* https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa - `TODO`

### issues

* https://github.com/pgjdbc/pgjdbc/issues/1102
* https://vkuzel.com/spring-boot-jpa-hibernate-atomikos-postgresql-exception
