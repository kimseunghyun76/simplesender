package com.ksh.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyHttpRequest {
    @NotEmpty(message="it is really a required targetUrl.")
    String targetUrl;
    @NotEmpty(message="it is really a required method.")
    String method;
    List<MyHttpHeader> headers;
    String body;
}
