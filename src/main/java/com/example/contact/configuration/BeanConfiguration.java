package com.example.contact.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class BeanConfiguration {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }


}
