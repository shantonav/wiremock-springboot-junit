# In-memory wiremock usage in Spring boot integration testing

The idea of this project is to demonstrate if there are two services A and B and A consumes B, then even if B is not avaialble or undergoing change but the contract to consume B is same, then we can use in-memory WireMock to test the service call to B from A and test the hypothesis of the flow in service A. This project uses wiremock stubs to respond to calls from A to B and verifies that at least one call has been done.

## Getting Started

Two simple Spring boot projects service-a and service-b . Both expose REST APIs. service-a consumes service-b. We are going to start only service-a and do an integration test of its REST API (with service-b being down or unavailable).

### Prerequisites

```
- you need to have Maven at least 3.3.x + (download from https://archive.apache.org/dist/maven/maven-3/)
- JDK 8 (download from here : https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- git client to checkout the repository (download from here: https://git-scm.com/downloads)
```

### Installing





```
- First checkout the Git repository : git@github.com:shantonav/wiremock-springboot-junit.git using
git clone git@github.com:shantonav/wiremock-springboot-junit.git

- You can browse through the code using your favourite IDE (I use Intellij)

- Go to service-a : cd service-a 
mvn clean package

Here you will notice the Spring boot JUnit test case : com.wiremock.exmaple.servicea.ServiceAApplicationTests

- Go to service-b : cd ../service-b (if you already are under service-a) 
mvn clean package
  
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
