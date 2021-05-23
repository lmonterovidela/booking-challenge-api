# booking-challenge-api

The application allows the management of bookings.

## How to run?

To build this project, make sure that mvn command is available in the path and your current working directory is the root of the project, execute the following from a project root directory.

There are two possibilities to run the project locally:
1. Docker compose: 

```
  1. mvn clean package spring-boot:build-image (Generate images)
  2. docker-compose up (Run images and rabbitmq)
```

2. Mvn:

```
  1. docker-compose -f docker-compose.rabbit.yml up (Start RabbitMQ)
  2. mvn clean spring-boot:run -pl consumer
  3. mvn clean spring-boot:run -pl producer
```

By default:
- service should be available at http://localhost:8081/ for create, update and delete bookings.
- service should be available at http://localhost:8082/ for get bookings.
- RabbitMQ managment be available at http://localhost:15672/#/ with user: guest and password: guest.
- H2 managment be available at http://localhost:8082/h2 with user: sa.


## Api details:

**Available methods**

| Method                                              | Description            |
|-----------------------------------------------------|------------------------|
| GET /v{versionId}/bookings/{bookingId}              | Get booking by its ID  |
| POST /v{versionId}/bookings                         | Create a new booking   |
| PUT /v{versionId}/bookings/{bookingId}              | Update booking         |
| DELETE /v{versionId}/bookings/{bookingId}           | Delete booking         |


## Models:

>GET /bookings/{bookingId}

**Request**

N/A

**Response Successfully**

HTTP_CODE : 200

```
{
    "id": 1,
    "name": "Jhon",
    "contactNumber": "12322",
    "pickupTime": "2010-12-12T00:00:00.000+0000",
    "asap": true,
    "waitingTime": 123,
    "numberOfPassengers": 123,
    "price": 12.12,
    "rating": 12,
    "lastModifiedOn": "2021-05-23T23:15:58.492+0000",
    "createdOn": "2021-05-23T23:15:58.492+0000",
    "tripWaypoints": [
        {
            "id": 2,
            "locality": "St Julian",
            "latitude": "121",
            "longitude": "132"
        },
        {
            "id": 3,
            "locality": "Sliema",
            "latitude": "12",
            "longitude": "13"
        }
    ]
}
```

>POST /bookings

**Request**

```
{
   "name":"Ben",
   "contactNumber":"12322",
   "pickupTime":"2010-12-12",
   "asap":true,
   "waitingTime":123,
   "numberOfPassengers":123,
   "price":12.12,
   "rating":12,
   "tripWaypoints":[
      {
         "latitude":12,
         "longitude":13,
         "locality":"Madrid"
      },
      {
         "latitude":121,
         "longitude":132,
         "locality":"Sliema"
      }
   ]
}
```

**Response Successfully**


HTTP_CODE if is successfully: 204


>PUT /bookings/{bookingId}

**Request**

```
{
   "name":"Ben",
   "contactNumber":"12322",
   "pickupTime":"2010-12-12",
   "asap":true,
   "waitingTime":123,
   "numberOfPassengers":123,
   "price":12.12,
   "rating":12,
   "tripWaypoints":[
      {
         "latitude":12,
         "longitude":13,
         "locality":"Madrid"
      },
      {
         "latitude":121,
         "longitude":132,
         "locality":"Sliema"
      }
   ]
}
```

**Response Successfully**


HTTP_CODE if is successfully: 204

>DELETE /bookings/{bookingId}

**Request**

N/A

**Response Successfully**


HTTP_CODE if is successfully: 204

## Comments:
- The endpoints v{version}/bookings/{id}/waypoints* were not built due to lack of context and for simplicity
- The GetById operation was built in the consumer module for simplicity, also in the case of having been built in a different module (query), the h2 database should have been configured as a server. I assumed that the main objective of the application was to keep the producer and consumer modules separate and a correct topology in RabbitMQ
- Get operation with query params was not built due to lack of context
