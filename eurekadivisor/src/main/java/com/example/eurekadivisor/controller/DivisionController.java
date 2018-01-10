package com.example.eurekadivisor.controller;

import com.example.eurekadivisor.service.NumberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DivisionController {

    @Autowired
    private NumberService numberService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String info(){
        return "/division";
    }

    @RequestMapping("/division")
    public String division() {
        List<ServiceInstance> instances =  discoveryClient.getInstances("number-service");
        if (instances.size() == 0){
            return "number-service required";
        }

        numberService.refreshUrl(instances.get(instances.size()-1).getUri().toString());
        float f = Float.valueOf(numberService.getFloat());
        int i = Integer.valueOf(numberService.getInt());

        float result = (float) f / i;

        return String.valueOf(result);
    }

}
