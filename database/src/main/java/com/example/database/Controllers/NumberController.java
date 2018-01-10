package com.example.database.Controllers;

import com.example.database.data.Number;
import com.example.database.data.NumberRepository;
import com.example.database.services.NumberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NumberController {

    @Autowired
    private NumberService numberService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private NumberRepository numberRepository;

    @RequestMapping("/")
    public String info(){
        return "/numbers<br>/history";
    }

    @RequestMapping(value = "/numbers", method = RequestMethod.GET)
    public String numbers(){
        List<ServiceInstance> instances =  discoveryClient.getInstances("number-divisor");
        if (instances.size() == 0){
            return "number-divisor required";
        }

        numberService.setUrl(instances.get(instances.size()-1).getUri().toString());

        Number n = new Number();
        n.setValue(numberService.getFloat());

        numberRepository.save(n);

        Iterable<Number> numberList = numberRepository.findAll();
        String output = "";

        for (Number num: numberList) {
            output = num.toString() + output;
        }


        return output;
    }

    @RequestMapping(value="/history", method = RequestMethod.GET, produces = "application/json")
    public List history(){
        List<Number> numbers = new ArrayList<>();
        Iterable<Number> iterNumber = numberRepository.findAll();
        iterNumber.forEach(numbers::add);
        return numbers;
    }
}
