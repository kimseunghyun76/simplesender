package com.ksh.mockup.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Data
@Table(name = "t_clientresponse")
public class ClientResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    private String method;
    private String dstMrn;
    private String srcMrn;
    private String uuid;
    @Column(length=500)
    private String AccessToken;
    @Lob
    private String HexSignedData;
    @Lob
    private String payload;

/*    @Temporal(TemporalType.TIMESTAMP)
    private Date  regDate;*/
    private LocalDateTime regDate;

    public String getRegDate(){
        String formatDateTime = regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatDateTime;
    }
}
