
### Development notes and decisions: 

- the application is composed out of 2 deployable wars - the RESTful service and the client application (the UI)

- to drive the REST work, I used two existing help jars - I am including the code for these as well: 
-- rest-common - some base classes useful to quickly bootstrap a RESTful project; focus on some of the RESTful constraints - discoverability/HATEOAS, query support, etc
-- rest-test - base test classes meant to ensure the correctness of the service



### Technology stack and Testing Notes: 

- the technology stack is: Spring 3.1, XStream, Jackson, H2 (configurable)
- the testing stack: Mockito, HTTP Core, HTTP Client, REST-assured
- testing suites: unit tests (naming convention: FooUnitTest), persistence level integration tests: (*Persistence*IntegrationTest), REST integration tests (*RESTIntegrationTest)



### Build instructions

- to build the project: in the root: 
`mvn clean install`

- to run the integration tests: in the root: 
`mvn clean install -Pintegration`



### Run and Usage instructions
- deploy the two provided wars
- access the UI at: 
http://localhost:8080/cardSample-web/

- query the RESTful API at: 
http://localhost:8080/cardSample-rest/api/clientcard?page=0&size=5
http://localhost:8080/cardSample-rest/api/clientcard?q=name=ClientCard1



### Functionality and UI notes: 

- known issues: 
-- the selection in the table doesn't visually highlight the selected row
-- the mapping is only unidirectional (clicking on a BusinessCard will show the ClientCards associatied with it - not the reverse)
-- simulating the addition of a ClientCard to a BusinessCard is a bit hardcoded (happens against the first element in the list)
- the operation itself is does not completely adhere to RESTful constraints


### Overall notes
Since the job description was mostly back end focused, I made the decision to start service first - 
the service for the two resources should be much more complete than the requirements state. 
The implementation took me about 5 hours - spent more time than I would have liked getting to know datatables in order to make the UI as usable as possible. 
There are of course a host of issues with the usability and maturity of the code - given the time boxed nature of the entire effort, I see these as known and assumed 
technical dept. For instance, the UI project could be further cleaned up (may contain unnecessary resources as I started a POC I had); then the mapping between the two types 
of cards should become bi-directional; then, on the notification operations - a further discussion around that would be useful given the time, as well as 
a more RESTful actual implementation. 

Thank you for the opportunity. 
Eugen Paraschiv. 