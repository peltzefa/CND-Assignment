package com.example.eurekadivisor.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@Service
public class NumberService {


    private RestTemplate restTemplate = new RestTemplate();
    private String url;

    public String getInt() {
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/number/int", String.class);
        return response.getBody();
    }

    public String getFloat() {
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/number/float", String.class);
        return response.getBody();
    }

    public void refreshUrl(String url) {
        this.url = url;
    }
}
