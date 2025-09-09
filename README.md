# Survivor Management API

A Spring Boot + MongoDB REST API for managing survivors and resources in a zombie apocalypse.
This project was built as a coding test to demonstrate clean
architecture, REST design, and database integration.

---

# Features
1. Add new survivors with profile and resources.
2. Update survivor location (latitude/longitude).
3. Mark survivors as infected.
4. Provide summary reports: 
   - Percentage of infected vs non-infected
   - Average resources per non-infected survivor.

---

# Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data MongoDB
- Validation (Jakarta Bean Validation)
- MongoDB Community Server and Compass

---

# Setup

You will need these installed:
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MongoDB Community Server (default on localhost:27017)](https://www.mongodb.com/try/download/community)
- [MongoDB Compass (optional GUI)](https://www.mongodb.com/try/download/compass)
- An IDE of your choice

### Clone and Build

```
git clone https://github.com/Sharen-Rajenthiran/survivor-management-rest-api
cd survivor-management-rest-api
mvn clean install
```

### Configure MongoDB

Check ```src/main/resources/application.properties```:

```
spring.data.mongodb.uri=mongodb://localhost:27017/survivor_db
```

### Run
```
mvn spring-boot:run
```

API will start at ```http://localhost:8080```

### Sanity Test

Check if MongoDB community server is running on Windows on powershell:

```
netstat -ano | findstr 27017
```

Once you created a survivor, you can use MongoDB compass
to check if the survivor is in the database. Make sure
you already connected to server prior.

---

# API Documentation

### Survivors
- POST ```/survivors``` -> create a survivor
- PATCH ```/survivors/{id}/location``` -> update location
- PATCH ```/survivors/{id}/infected``` -> mark infected
- GET ```/survivors``` -> list all survivors

### Report
- GET ```/reports/summary``` -> infected % and average resources

### Request body to create a survivor

```
{
    "name": "Damon",
    "age": 20,
    "gender": "male",
    "lastLocation": { "latitude": 50.509865, "longitude": -0.018192 },
    "inventory": [
      { "resourceName": "water", "quantity": 1 },
      { "resourceName": "food", "quantity": 1 }
    ]
}
```

### Response body once a survivor is created

```
{
    "name": "Damon",
    "age": 20,
    "gender": "male",
    "lastLocation": {
        "latitude": 50.509865,
        "longitude": -0.018192
    },
    "inventory": [
        {
            "resourceName": "water",
            "quantity": 1
        },
        {
            "resourceName": "food",
            "quantity": 1
        }
    ],
    "id": "xxx",
    "infected": false
}
```

### Request body to update a survivor's location

You can get the id from MongoDB compass or from the response of created survivor.

```
{
    "latitude": 40.7128,
    "longitude": -74.0060
}
```

### Response body after location is updated

```
{
    "name": "Bob",
    "age": 25,
    "gender": "male",
    "lastLocation": {
        "latitude": 40.7128,
        "longitude": -74.006
    },
    "inventory": [
        {
            "resourceName": "water",
            "quantity": 1
        },
        {
            "resourceName": "food",
            "quantity": 1
        }
    ],
    "id": "xxx",
    "infected": false
}
```

### Request body to mark a survivor is infected

```
{
    "infected": true
}
```

### Response body once survivor is marked infected

```
{
    "name": "Damon",
    "age": 20,
    "gender": "male",
    "lastLocation": {
        "latitude": 50.509865,
        "longitude": -0.018192
    },
    "inventory": [
        {
            "resourceName": "water",
            "quantity": 1
        },
        {
            "resourceName": "food",
            "quantity": 1
        }
    ],
    "id": "xxx",
    "infected": true
}
```

### Response body after listing all survivors

```
[
    {
        "name": "Alice",
        "age": 30,
        "gender": "female",
        "lastLocation": {
            "latitude": 51.509865,
            "longitude": -0.118092
        },
        "inventory": [
            {
                "resourceName": "water",
                "quantity": 3
            },
            {
                "resourceName": "food",
                "quantity": 5
            }
        ],
        "id": "xxx",
        "infected": false
    },
    {
        "name": "Bob",
        "age": 25,
        "gender": "male",
        "lastLocation": {
            "latitude": 40.7128,
            "longitude": -74.006
        },
        "inventory": [
            {
                "resourceName": "water",
                "quantity": 1
            },
            {
                "resourceName": "food",
                "quantity": 1
            }
        ],
        "id": "xxx",
        "infected": false
    },
    {
        "name": "Damon",
        "age": 20,
        "gender": "male",
        "lastLocation": {
            "latitude": 50.509865,
            "longitude": -0.018192
        },
        "inventory": [
            {
                "resourceName": "water",
                "quantity": 1
            },
            {
                "resourceName": "food",
                "quantity": 1
            }
        ],
        "id": "xxx",
        "infected": true
    }
]
```

### Response Body for report % infected vs non-infected

```
{
    "average_resources_per_non_infected_survivor": {
        "water": 2.0,
        "food": 3.0
    },
    "total_survivors": 3,
    "non_infected_percentage": 66.66666666666667,
    "infected_count": 1,
    "infected_percentage": 33.333333333333336,
    "non_infected_count": 2
}
```

---
# Improvements

- API pagination and limit
- Swagger/OpenAPI documentation
- Improve testing as testing was not implemented for this project



---
Author: Sharen Rajenthiran
---



