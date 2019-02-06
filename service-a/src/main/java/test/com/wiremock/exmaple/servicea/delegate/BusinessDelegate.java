package test.com.wiremock.exmaple.servicea.delegate;

import test.com.wiremock.exmaple.servicea.domain.ServiceResponse;

public interface BusinessDelegate<T,K> {
    public <T extends ServiceResponse> T doTheBusiness(K domain);
}
