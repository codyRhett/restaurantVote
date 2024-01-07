# restaurantVote
Voting System for restaurants

Version 0.0.1 - REST HTTP MAVEN

Service is developed using Spring Boot, Spring MVC, Spring Security, Spring Data, Liquibase, Hibernate

Backend for Voting System for the best Restaurant

psql -U postgres -W -h localhost

Functionality
User can be authentiphicated using endpoint - http://localhost:8080/api/registration/user with example body:
{
    "username":"cody",
    "firstName":"artem1",
    "lastName":"ryvkin1",
    "password":"cody1234",
    "email":"cod1y@ya.ru"
}

Password will be encrypted and only user with ADMIN role has an access to get list of all users and delete users

Endpoint to get All Users - http://localhost:8080/api/admin/users
Endpoint to delete Users - http://localhost:8080/api/admin/user/delete/22

Also User can register new Restaurant, but this user must be authentiphicated

User can vote for one restaurant only once, but he/she can vote for infinite amount of restaurants

Vote Endpoint: http://localhost:8080/api/vote/save with example body
{
    "restaurant": {
        "id": 41,
        "name":"McDonalds",
        "cuisine":"USA"
    },
    "rating": 10,
    "userId": 22
}

User can get all the Restaurants sorted by Name: http://localhost:8080/api/restaurant/list?sort=name,ASC
