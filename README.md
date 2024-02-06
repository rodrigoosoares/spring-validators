# Spring Framework Constraint Validator

This is a simple project to use the constraint validator from Spring Framework.

This project container the tests to test these validation.

You can use this curl to test the validations:
```shell
curl --location 'http://localhost:8080/character' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Aragorn",
  "healthPoints": 100,
  "attack": 50,
  "defense": 30,
  "stamina": 80,
  "intelligence": 60,
  "status": "HEALTH"
}
```