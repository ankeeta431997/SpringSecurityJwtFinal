package com.unoveo.securityjwt.calculator;

public class ExpressionRequest {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ExpressionRequest(String type, String value) {
        this.type = type;
        this.value = value;
    }


    @Override
    public String toString() {
        return "ExpressionRequest{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
