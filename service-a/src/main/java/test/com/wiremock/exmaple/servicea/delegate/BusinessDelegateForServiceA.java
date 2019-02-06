package test.com.wiremock.exmaple.servicea.delegate;

import test.com.wiremock.exmaple.servicea.communication.ServiceCommunicator;
import test.com.wiremock.exmaple.servicea.domain.MyDomainObject;
import test.com.wiremock.exmaple.servicea.domain.OperationAResponse;
import test.com.wiremock.exmaple.servicea.domain.ServiceBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessDelegateForServiceA implements BusinessDelegate<OperationAResponse, MyDomainObject> {

    private ServiceCommunicator serviceCommunicator;

    @Autowired
    public BusinessDelegateForServiceA(ServiceCommunicator serviceCommunicator){
        this.serviceCommunicator = serviceCommunicator;
    }

    @Override
    public OperationAResponse doTheBusiness(MyDomainObject domain) {
        return transformResponses(serviceCommunicator.getServiceBResponse(domain));
    }

    private OperationAResponse transformResponses(ServiceBResponse serviceBResponse) {
        final OperationAResponse operationAResponse =
                new OperationAResponse(serviceBResponse.getSomeTextForB(),serviceBResponse.getSomeIntForB());
        return operationAResponse;
    }
}
