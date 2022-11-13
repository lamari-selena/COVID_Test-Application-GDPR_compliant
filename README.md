# COVID_Test-Application-GDPR_compliant

This part is presented as an installation guide in order to help the users of the application in setting up the local environment.

To be able to deploy the application, it is first necessary to clone the code which is located on two different folders 
(one for the front and the other for the back). To do this, go to your working folder and run the git command to clone the :

1) Setting up the local environment for the backend

Before you can run the application locally, you need to install some tools. If you have not already installed them, please proceed to the
installation of the following applications the following applications:
- Docker: https://docs.docker.com/get-docker/
- Java: https://www.java.com/fr/download/manual.jsp

We recommend using the Intellij IDE for backend development because it integrates very well with the Java environment and many plugins are available 
to make certain tasks easier for the developer. We will continue this installation guide using the Intellij IDE.

After opening the project (back-end) on Intellij, you must first build the jars of the microservices and build the docker images :

# Build the jars of the microservices: 
  Gradle > covid-alert-backend > Tasks > build > bootjar
  (allows to assemble a jar archive containing the main classes and their dependencies)
  
# Build docker images of microservices:
  Gradle > covid-alert-backend > Tasks > docker > docker
  
# To build a jar or an image of a particular microservice:
  Gradle > covid-alert-backend > microserviceName > Task > build > bootJar

  Gradle > covid-alert-backend > microserviceName > Task > docker > docker
  
After having built the executable jars of the microservices and their docker images, you can launch the application in the following way:
First start by launching the zookeeper, kafka, keycloak, discovery-server and config-server :

 - docker-compose up -d zookeeper kafka keycloak discovery-service config-server
 
Then the other services :
  - docker-compose up -d

You have to use these commands at the root level of the project (where the docker-compose.yml file is located in the Project). This will create a container for each microservice.

The back is now functional.
