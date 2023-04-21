## RabbitMQ-SpringBoot-WebFlux
An implementation of RabbitMQ with SpringBoot WebFlux 

## Prerequisites
Install rabbitMQ and make sure its running locally

## Fork/CLone 

Clone the repository locally and run the services

## Services
## queue 
service with api to add to queue
`
endpoint:http://localhost:8090/create-order
body

{
"items": [
{
"name": "apples",
"price": 30
},
{
"name": "mango",
"price": 30
},
{
"name": "banana",
"price": 30
}

    ]
`

## consumefromapi
service to consume from the queue


## What Next

Working on adding what i have consumed from the queue to the database.


