package com.ksh.mockup.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MccMqController {


    @CrossOrigin(maxAge = 3600)
    @RequestMapping("/queuelist")
    @ResponseBody
    public String  getQueueList() throws Exception{
        HttpResponse<String> response = Unirest.get("http://localhost:15672/api/queues")
                .header("authorization", "Basic Z3Vlc3Q6Z3Vlc3Q=")
                .header("cache-control", "no-cache")
                .asString();
        return response.getBody().toString();
    }
}
