package com.ksh.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyHttpResponse {

    @NotEmpty(message="it is really a required responseCode.")
    int responseCode;
    Map<String, String> headers;
    String payload;

    public MyHttpResponse(int responseCode, Map<String, String> headers, String payload){
        this.responseCode = responseCode;
        this.headers = headers;
        this.payload = payload;
    }
}
