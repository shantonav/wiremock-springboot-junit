package com.wiremock.exmaple.servicea.communication;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.wiremock.exmaple.servicea.domain.MyDomainObject;
import com.wiremock.exmaple.servicea.domain.ServiceBResponse;


public interface ServiceCommunicator {
    WebResource createWebResource();

    Client createClient();

    default ClientConfig createClientConfig(){
        final ClientConfig config = new DefaultClientConfig();
        return config;
    }

    void addFilter(ClientFilter filter);

    ServiceBResponse getServiceBResponse(MyDomainObject domainObject);

}
