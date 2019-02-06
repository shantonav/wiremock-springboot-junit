package test.com.wiremock.exmaple.servicea.controller;

import test.com.wiremock.exmaple.servicea.delegate.BusinessDelegate;
import test.com.wiremock.exmaple.servicea.domain.MyDomainObject;
import test.com.wiremock.exmaple.servicea.domain.OperationAResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(path = "/servicea")
public class TestController {

    @Autowired
    private BusinessDelegate<OperationAResponse, MyDomainObject> businessDelegate;

    @PostMapping(path = "/operationa", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<OperationAResponse> operationAHandler(@RequestBody @Valid MyDomainObject domainObject){


        return new ResponseEntity<>(
                (OperationAResponse)businessDelegate.doTheBusiness(domainObject), HttpStatus.CREATED);
    }
}
