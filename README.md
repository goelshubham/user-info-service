# user-info-service
Backend microservice to store and retrieve user information

This application is used for collecting users information and allow us to pull up & enter information about them.

### Technologies used
Java 1.8, Spring boot, Embedded H2 database, Spring Data, Eclipse IDE, JUnit

### Execution steps
This project can be cloned from Git. Go to root directory of the project and execute following command in command line.

```mvn spring-boot:run```

This will start the application on localhost server with port 8090. 

This microservice exposes following three APIs

1. userinfoapi/v1/user 
This can be used to create a new user in the system
Details:
Operation: POST 
Endpoint: http://localhost:8090/userinfoapi/v1/user

Sample request:
```
{
	"email": "test@yahoo.com",
	"userName": "test_name",
	"country": "US",
	"phoneNumber": "716-3499290"
}
```

Sample response:
```
{
    "userId": "wkLMhLfiui"
}
```

2. userinfoapi/v1/usage
This can be used to create user's usage details in the system

3. 
