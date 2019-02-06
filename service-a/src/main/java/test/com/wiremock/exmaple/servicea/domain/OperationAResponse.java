package test.com.wiremock.exmaple.servicea.domain;

public class OperationAResponse implements ServiceResponse{
    private String someResponseText;
    private Integer someResponseCode;

    public OperationAResponse(String someResponseText, Integer someResponseCode) {
        this.someResponseText = someResponseText;
        this.someResponseCode = someResponseCode;
    }

    public OperationAResponse() {
    }

    @Override
    public String toString() {
        return "OperationAResponse{" +
                "someResponseText='" + someResponseText + '\'' +
                ", someResponseCode=" + someResponseCode +
                '}';
    }

    public String getSomeResponseText() {
        return someResponseText;
    }

    public void setSomeResponseText(String someResponseText) {
        this.someResponseText = someResponseText;
    }

    public Integer getSomeResponseCode() {
        return someResponseCode;
    }

    public void setSomeResponseCode(Integer someResponseCode) {
        this.someResponseCode = someResponseCode;
    }
}
