package com.wiremock.exmaple.servicea.communication;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.wiremock.exmaple.servicea.domain.DomainObject;
import com.wiremock.exmaple.servicea.domain.ServiceResponse;
//import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.http.HttpMethod;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class AbstractServiceCommunicator<T extends ServiceResponse> implements  ServiceCommunicator{
    private Client client;

    @Override
    public  abstract WebResource createWebResource() ;

    @PostConstruct
    @Override
    public Client createClient() {
        final ClientConfig clientConfig = createClientConfig();
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(clientConfig);
        return client;
    }

    @Override
    public void addFilter(ClientFilter filter) {
        client.addFilter(filter);
    }

    protected  Client getClient(){ return this.client; }

    protected  T communicte(DomainObject domainObject, Class<? extends ServiceResponse> repsonseType,HttpMethod httpMethod){
        Client client = createClient();

        WebResource webResource = createWebResource();
        T response = null;

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date now = new Date();
        SimpleDateFormat formatedDt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

        String formattedDate = formatedDt.format(now).toString();



        if (HttpMethod.POST.equals(httpMethod) ){
            response = (T)webResource.header("Date", formattedDate)
                    .header("Content-type", MediaType.APPLICATION_JSON)
                    .post(repsonseType  ,domainObject);
        }else if (HttpMethod.GET.equals(httpMethod)) {
            response = (T)webResource.header("Date", formattedDate)
                    .header("Content-type", MediaType.APPLICATION_JSON)
                    .get(repsonseType);
        }



        return response;

    }
}
