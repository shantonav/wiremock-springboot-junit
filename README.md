# In-memory wiremock usage in Spring boot integration testing

The idea of this project is to demonstrate :
if there are two services A and B and A consumes B, then even if B is not avaialble or undergoing change but the contract to consume B is same, then we can use in-memory WireMock to test the service call to B from A and test the hypothesis of the flow in service A. This project uses wiremock stubs to respond to calls from A to B and verifies that at least one call has been done.

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

Here you will notice the Spring boot JUnit test case : com.wiremock.exmaple.servicea.ServiceAApplicationTests ( we will talk about this in detail later, read-on ).

- Go to service-b : cd ../service-b (if you already are under service-a) 
mvn clean package
  

```


## Running the tests

Let us go through test case : _**com.wiremock.exmaple.servicea.ServiceAApplicationTests.testServiceBCallThroughAWireMock**_ in detail

It is a Spring boot test which loads the context .

The test uses a "test" profile that overrided the end-point to service-b to point to Wiremock instance instead of the actual service itself.

```
@ActiveProfiles(value = "test")

this lets Spring use the application-test.properties as the configuration 
and this configuration points service-b URL to : 

service.b.url=http://localhost:12111
```

The test also uses Spring boot MockMvc to consumer service-a API. And for that we use
```
@AutoConfigureMockMvc

this enables to the HTTP mock servlet withing the unit test
```

The all important part of the test is setting up the Wire mock using JUnit @Rule

```
    @Rule
	public WireMockRule wireMockRule = new WireMockRule(12111);
	
	This starts the Wiremock server at localhost listening to port 12111

```

Notice how the wiremock stub is created to the downstream service-b in a DSL style
function calls. 

```
stubFor(post(urlEqualTo("/serviceb/operationb"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", javax.ws.rs.core.MediaType.APPLICATION_JSON)
						.withBody(returnJSON)));
The hypothesis being tested here is:
- given a JSON request to service-a api /servicea/operationa listening on http POST
- the API would return a JSON response 
- with HTTP status code 201
- the API consumes and produces JSON
```

It is important to note 
here that the objective of this demonstration is not to do end-2-end testing of API in
service-a . We want to test the logical flow of api /servicea/operationa and to allow
it to cross HTTP boundary but not call service-b , instead invoke wiremock stub (induced 
behaviour in the application using modified end point and wiremock). 
Then the flow further moves on with its flow.

The test does verify whether we crossed HTTP boundary and invoked the wiremock stub 
instead of the real service-b.

```

verify(postRequestedFor(urlEqualTo("/serviceb/operationb"))
				.withHeader("Content-Type", equalTo(javax.ws.rs.core.MediaType.APPLICATION_JSON)));

```


## Conslusion

This kind of stubeed integration testing gives the developer the much required confidence
to do edge case testing where the api under test consumes a downstrea service.
Especially suited for microservice architecture
speeds up development lifecycle with quicker feedbacks and lesser heavy lifting
at a much reduced boiler plate code for stubbing.

