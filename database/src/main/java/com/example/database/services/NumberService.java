package com.example.database.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumberService {

    private RestTemplate restTemplate = new RestTemplate();
    private String url;


    public void setUrl(String url){
        this.url = url;
    }

    public float getFloat() {
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/division", String.class);
        Float f = Float.parseFloat(response.getBody());
        return f;
    }
}
