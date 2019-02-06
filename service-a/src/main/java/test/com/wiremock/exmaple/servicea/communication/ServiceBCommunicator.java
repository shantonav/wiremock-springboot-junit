package test.com.wiremock.exmaple.servicea.communication;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import test.com.wiremock.exmaple.servicea.domain.MyDomainObject;
import test.com.wiremock.exmaple.servicea.domain.ServiceBResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;


@Service
public class ServiceBCommunicator extends AbstractServiceCommunicator<ServiceBResponse> implements  ServiceCommunicator{
    @Value("${service.b.url}")
    private String serviceBURL;

    @Value("${service.b.uri}")
    private String serviceBURI;

    @Value("${service.b.method}")
    private String httpMethod;



    @Override
    public WebResource createWebResource() {

        final WebResource resource = getClient().resource(this.serviceBURL+this.serviceBURI);

        return resource;
    }

    public ServiceBResponse getServiceBResponse(MyDomainObject domainObject){
        addFilter(new LoggingFilter(System.out));
        ServiceBResponse response = communicte(domainObject,ServiceBResponse.class, HttpMethod.resolve(httpMethod));
        return response;
    }
}
