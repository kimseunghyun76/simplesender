package com.ksh.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyHttpHeader {
    String name;
    @NotEmpty(message="it is really a required value.")
    String value;
}
