
Development notes and decisions: 

- the application is composed out of 2 deployable wars - the RESTful service and the client application (the UI)

- to drive the REST work, I used two existing help jars - I am including the code for these as well: 
-- rest-common - some base classes useful to quickly bootstrap a RESTful project; focus on some of the RESTful constraints - discoverability/HATEOAS, query support, etc
-- rest-test - base test classes meant to ensure the correctness of the service

