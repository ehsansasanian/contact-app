package com.example.contact.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Configuration
public class BeanConfiguration {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public HttpRequest.Builder getHttpRequestBuilder(){
        return HttpRequest.newBuilder();
    }

}
