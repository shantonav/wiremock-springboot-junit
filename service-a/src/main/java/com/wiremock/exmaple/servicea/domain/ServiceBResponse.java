package com.wiremock.exmaple.servicea.domain;

public class ServiceBResponse implements ServiceResponse{
    public ServiceBResponse(String someTextForB, Integer someIntForB) {
        this.someTextForB = someTextForB;
        this.someIntForB = someIntForB;
    }

    public ServiceBResponse() {
    }

    private String someTextForB;
    private Integer someIntForB;

    @Override
    public String toString() {
        return "ServiceBResponse{" +
                "someTextForB='" + someTextForB + '\'' +
                ", someIntForB=" + someIntForB +
                '}';
    }

    public String getSomeTextForB() {
        return someTextForB;
    }

    public void setSomeTextForB(String someTextForB) {
        this.someTextForB = someTextForB;
    }

    public Integer getSomeIntForB() {
        return someIntForB;
    }

    public void setSomeIntForB(Integer someIntForB) {
        this.someIntForB = someIntForB;
    }
}
