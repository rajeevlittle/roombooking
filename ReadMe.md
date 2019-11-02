1.1. register user api

post http://localhost:8080/users

{
	"name": "name",
	"surname":"surname",
	"login":"login",
	"password":"password"
}

1.2. get all users

get http://localhost:8080/users

1.3. update user

put http://localhost:8080/users/{id}

{
	"name":"name",
	"surname":"surname",
	"login":"login",
	"password":"password"
}

1.4. delete user

delete http://localhost:8080/users/{id}

2.1 register room api

post http://localhost:8080/rooms

{
	"roomName":"room name",
	"description" : "description",
	"numberOfSeats" : 20,
	"projector" : 1,
	"phoneNumber" : "00000000"
}

2.2 get all rooms

get http://localhost:8080/rooms

2.3 update room

put http://localhost:8080/rooms/{id}

{
	"roomName":"room name",
	"description" : "description",
	"numberOfSeats" : 20,
	"projector" : 1,
	"phoneNumber" : "00000000"
}

2.4 delete room

delete http://localhost:8080/rooms/{id}

3.1 booking room

post http://localhost:8080/booking/new

{
    "room_name": "room name",
    "user_login": "login",
    "user_pwd": "password",
    "date_from": "2019-03-12 00:00:00",
    "date_to": "2019-03-23 00:00:00"
}

3.2 get all booking info



{
    "date_from": "2019-03-12 00:00:00",
    "date_to": "2019-03-23 00:00:00"
}

3.3 get booking info by room name

get http://localhost:8080/bookings/room/{name}

{
    "date_from": "2019-03-12 00:00:00",
    "date_to": "2019-03-23 00:00:00"
}

3.4 get booking info by user

get http://localhost:8080/bookings/user/{name}

{
    "date_from": "2019-03-12 00:00:00",
    "date_to": "2019-03-23 00:00:00"
}

