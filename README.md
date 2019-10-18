# project_catalog_api

Api which manages products and categories

	This is a product catalog api. It is written in java using the spring boot framework.
	It has two main microservices; a product service (product_service) and a category service(category_service). 
	As each name implies the microservices respectively handle operations on products and categories. 
	Also, there is a naming server for the api (the naming server is a eureka naming server).
	
## Getting started
1.  Have spring tool suite(STS) ide or sts integrated into a java ide of your choice
2.	Optionally have access to internet connection so as to download any dependencies on-the-go if needed.

## Running the api

1.	Clone the repository
2.  Import the project from the clone repository into an IDE equiped with STS.
3.	The application can then be run (as a spring boot app).
	
	It runs on the default port (8080), that is localhost:8080 is the root

## Understanding the api

	
The application has two controllers (each with several endpoints). 
A detailed overview of each enpoint can be seen in the swagger documentation: 
While the application is running in the IDE, in a browser, visit:

	localhost:8080/swagger-ui.html

