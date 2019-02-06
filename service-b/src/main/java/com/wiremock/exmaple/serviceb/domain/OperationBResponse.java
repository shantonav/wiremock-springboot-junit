package com.wiremock.exmaple.serviceb.domain;

public class OperationBResponse implements ServiceResponse{
    private String someTextForB;
    private Integer someIntForB;

    @Override
    public String toString() {
        return "OperationBResponse{" +
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

    public OperationBResponse(String someTextForB, Integer someIntForB) {
        this.someTextForB = someTextForB;
        this.someIntForB = someIntForB;
    }

    public OperationBResponse() {
    }
}
