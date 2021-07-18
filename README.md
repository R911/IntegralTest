# IntegralTest

This project is built using spring boot starter code 

https://spring.io/guides/gs/spring-boot/


You can use the following process provided by spring to run the application, but it would be easier to just run it inside an ide. (Didn't spend time on creating/checking the running of testing modules from command line interface)

== Run the Application

To run the application, run the following command in a terminal window (in the `complete`)
directory:

----
./gradlew bootRun
----
If you use Maven, run the following command in a terminal window (in the `complete`)
directory:

----
./mvnw spring-boot:run
----

# Code

The code is has a DAO layer, whic contains the objects for the User and Message. It also has a SOA layer which contains the main business logic for the features requested.

The testing is done on the SOA layer instead of the controller to avoid unnessary complexity. 

You can run the application from Application.java from the ide, which will start an internal server at port 8080.

Unit Test can be run from ApplicationTest.java file in the test directory.

Most of the request are in get format except for publish message request, which is post. It would be easier to use a client like Postman to manually test, see the code working.

Since its was built in a hurry, proper care was not provided for checking of the restful api (input checks, exception, status checks), as in reality a socket based approach would have been better.

A few examples for the rest api:

curl --location --request GET 'localhost:8080/network/create/user/?userName=Rahul'


curl --location --request POST 'localhost:8080/network/publish/message' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userID":"542c4ec5-10c6-4f24-be6b-f385403d47de",
    "message":"Nice Weather"

}'


curl --location --request GET 'localhost:8080/network/view/mytimeline?myId=542c4ec5-10c6-4f24-be6b-f385403d47de'

curl --location --request GET 'localhost:8080/network/follow/?myId=542c4ec5-10c6-4f24-be6b-f385403d47de&friendId=6755ff05-b3ec-4dcf-b5a3-50687bd5c768'

curl --location --request GET 'localhost:8080/network/view/friendTimeline/?myId=9efa4717-d090-4395-bd13-0ec8044ac662&friendId=37ee9792-5642-4ef7-8b0f-2bacffe9f507'
