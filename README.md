# booking-challenge-api

## How to run?

### Clarifications:
- The endpoints v{version}/bookings/{id}/waypoints* were not built due to lack of context and simplicity
- The GetById operation was built in the consumer module for simplicity, also in the case of having been built in a different module (query), the h2 database should have been configured as a server. I assumed that the main objective of the application was to keep the producer and consumer modules separate and a correct topology in RabbitMQ
- Get operation with query params was not built due to lack of context
