package test.com.wiremock.exmaple.servicea.domain;

import javax.validation.constraints.NotNull;

public class MyDomainObject implements  DomainObject{
    @NotNull
    private String someText;

    @NotNull
    private Integer someInteger;

    @Override
    public String toString() {
        return "MyDomainObject{" +
                "someText='" + someText + '\'' +
                ", someInteger=" + someInteger +
                '}';
    }

    public String getSomeText() {
        return someText;
    }

    public void setSomeText(String someText) {
        this.someText = someText;
    }

    public Integer getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(Integer someInteger) {
        this.someInteger = someInteger;
    }
}
