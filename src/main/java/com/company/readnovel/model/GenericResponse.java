package com.company.readnovel.model;

public class GenericResponse {
    public Integer statusCode;
    public String timeStamp;
    public String message;

    public GenericResponse() {

    }

    public GenericResponse(Integer statusCode, String timeStamp, String message) {
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
