package com.ksh.mockup.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Value("${mcc.elasticsearch.host}")
    private String host;

    @Value("${mcc.elasticsearch.port}")
    private int port;

    @Bean
    public RestClient client() throws Exception{

        RestClient restClient = RestClient.builder(
                new HttpHost(host,port,"http")
        ).build();

        return restClient;
    }
}
