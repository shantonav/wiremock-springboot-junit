package com.wiremock.exmaple.serviceb.controller;

import com.wiremock.exmaple.serviceb.domain.OperationBResponse;
import com.wiremock.exmaple.serviceb.domain.ServiceBDomainObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;


@RestController
@RequestMapping(path = "/serviceb")
public class ServiceBController {
    Logger log = LoggerFactory.getLogger(ServiceBController.class);
    private static Integer count = Integer.valueOf(0);

    @PostMapping(path = "/operationb", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<OperationBResponse> operationAHandler(@RequestBody @Valid ServiceBDomainObject domainObject){
        log.info("Received request no: "+(++count));
        OperationBResponse operationBResponse =
                new OperationBResponse("I am service B response for "+domainObject.getSomeText(),
                        Integer.valueOf(domainObject.getSomeInteger())+Integer.valueOf(150));

        return new ResponseEntity<>(
                operationBResponse, HttpStatus.CREATED);
    }
}
