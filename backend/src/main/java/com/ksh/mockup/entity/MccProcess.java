package com.ksh.mockup.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
public class MccProcess {

    private String name;
    private String ip;
    private int port;
    private String status;
    private String pid;
    private LocalDateTime updateDateTime;
    private long updateGap;
    public String getUpdateDateTime(){
        String formatDateTime = updateDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatDateTime;
    }
    private String sessionname;
    private String session;
    private String usedmem;
    private String memusingstatus;
    private String user;
    private String cputime;
    private String path;

}
