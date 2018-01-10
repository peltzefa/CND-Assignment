package com.example.eurekanumber.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class NumberService {

    @RequestMapping("/")
    public String info(){
        return "<b>/number/int</b><br><b>/number/float</b>";
    }

    @RequestMapping("/number/int")
    public String numberInt() {
        Random r = new Random();
        int i = r.nextInt(100);
        System.out.println("generated int: " + i);
        return Integer.toString(i);
    }

    @RequestMapping("/number/float")
    public String numberFloat() {
        Random r = new Random();
        float f = r.nextFloat();
        if (f<0){
            f*= -1;
        }
        f*=10;
        System.out.println("generated float: " + f);
        return Float.toString(f);
    }

}
