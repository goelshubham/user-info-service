# user-info-service
Backend microservice to store and retrieve user information

This application is used for collecting users information and allow us to pull up & enter information about them.

### Technologies used
Java 1.8, Spring boot, Embedded H2 database, Spring Data, Eclipse IDE, JUnit, Swagger

### Execution steps
This project can be cloned from Git. Go to root directory of the project and execute following command in command line.

```mvn spring-boot:run```

This will start the application on localhost server with port 8090. 

This microservice exposes following three APIs

1. userinfoapi/v1/user 
This can be used to create a new user in the system

Operation: HTTP POST 
Endpoint: http://localhost:8090/userinfoapi/v1/user

Request:
```
{
	"email": "test@yahoo.com",
	"userName": "test_name",
	"country": "US",
	"phoneNumber": "716-3499290"
}
```

Response:
```
{
    "userId": "wkLMhLfiui"
}
```



We can use APIs on swagger UI to test.

2. userinfoapi/v1/usage
This can be used to create user's usage details in the system

Operation: HTTP POST
http://localhost:8090/userinfoapi/v1/usage

Request Body requires three parameters - user id, usage type, and usage time

Request
```
{
	"userId": "wkLMhLfiui",
	"usageType": "SMS",
	"usageTime": "2020-01-15"
}
```

3. userinfoapi/v1/user/usage/{userId}/{usageType}/{startTime}

HTTP GET Operation

Request Endpoint:
http://localhost:8090/userinfoapi/v1/user/usage/wkLMshLfiui/SMsS/2021-01-01

Response:
```
{
  "id": "cCciBiTpLi",
  "email": "test@yahoo.com",
  "userName": "testname",
  "country": "US",
  "phoneNumber": "716-3499290",
  "usages": [
    {
      "id": 1,
      "usageType": "SMS",
      "usageTime": 1579064400000
    },
    {
      "id": 2,
      "usageType": "DATA",
      "usageTime": 1579064400000
    }
  ]
}
```


I have created the REST API documentation through swagger integration. After the service is up and running on localhost then swagger documentation can be found on URL - http://localhost:8090/swagger-ui.html


