package com.wiremock.exmaple.servicea.delegate;

import com.wiremock.exmaple.servicea.domain.ServiceResponse;

public interface BusinessDelegate<T,K> {
    public <T extends ServiceResponse> T doTheBusiness(K domain);
}
