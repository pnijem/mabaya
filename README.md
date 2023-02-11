# Mabaya Ad Service

## Background

As an ad tech company we want to develop a module that enables sellers to create campaigns for
promoting their products

## Entities

There are (at least) two main entities composing this module:

1. Product - simply represents a product with title, category, price and product-serial number (can
   be any int/string-sequence for the sake of this exercise)
2. Campaign - a group of products to promote(/advertise) with shared properties: start-date,
   bid. As well, the campaign has a name.
   a. Campaign is considered active for the 10 days following its start-date.
   b. Different campaigns may promote the same product

## APIs

The module should provide 2 APIs:

1. Create campaign - api for creating a campaign
   a. Parameters:
   i. name
   ii. startDate
   iii. List of product identifiers to promote.
   iv. Bid - the price seller is willing to pay for a click on a product advertised in this
   campaign
   b. Expected result: a campaign is created (with specified parameters) ready to promote all
   products
   c. Response - a json representation of the created campaign
2. Serve Ad - api to retrieve ads
   a. Parameters:
   i. Category - a String representing category of products
   b. Expected Result:
   i. the api should return a single promoted product, the one with the highest bid,
   belonging to active campaign/s from the specified category. If there are no
   promoted product for the matching category simply return a promoted product
   with the highest bid.
   ● For simplicity, if more than one product is found, you may return thefirst or random.
   ii. The response is in JSON format

## APIs Documentation

Please check the Swagger UI [page](http://localhost:8080/swagger-ui/index.html)
Also there is the actuator endpoints

## Working Locally

Please follow the steps below:

1. Install [maven](https://maven.apache.org/download.cgi) and set environment variable for it.
2. Install [Java](https://www.oracle.com/java/technologies/downloads/#jdk17-windows) 11 and set
   environment variable.
3. Add the required Active Spring Profile to the VM options i.e. `-Dspring.profiles.active=local`
4. Add `LOG_LEVEL` environment variable with the required value i.e. `INFO`, `ERROR`, `DEBUG`
5. Make sure mabaya-ad container is up
6. Start the app

## Working Locally Docker

1. Run from the root of this project`docker-compose up --build -d` command
2. Make sure mabaya-ad and mabaya-db containers are up
3. Run ``docker ps`` to have the list of container IDs for running the rest of the steps
4. Run ``docker exec -it <mabaya-db-container-id> sh`` to open shell from within the container and
   then run:

```shell
root:/var/www/html/sql_scripts# mysql -h mabaya-db -uroot -proot mabaya < mabaya_db.sql
root:/var/www/html/sql_scripts# exit
```

## Local Testing

## Logging

Log level can be changed using the environment variable LOG_LEVEL

## Suggestions & TODO

1. Cleanup inactive campaigns and move them to different table or other data store
2. Use Enum for categories
3. Spring Boot 3 + Java 17 + Native image support
4. Generics for the converter
5. Configurable active days
6. Use MongoDB as it is more natural for storing JSON based data (semi-structured)
7. Unit tests