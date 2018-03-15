package com.ksh.mockup.entity;

import lombok.Data;

@Data
public class VueTableRequest {

    private int  pagesize= 0;
    private String program="";
    private String control="";
    private String severity="";
    private String msgCode="";
    private String searchname="";
    private String searchvalue="";
    private String startDate="";
    private String endDate="";
}
