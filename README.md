# restaurantVote
Voting System for restaurants

Backend for Voting System for the best Restaurant

Functionality
1. User can be authentiphicated using endpoint - http://localhost:8080/api/registration/user with example body:
{
    "username":"cody",
    "firstName":"artem1",
    "lastName":"ryvkin1",
    "password":"cody1234",
    "email":"cod1y@ya.ru"
}

Password will be encrypted and only user with ADMIN role has an access to get list of all users and delete users

Endpoint to get All Users - http://localhost:8080/api/admin/users

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

