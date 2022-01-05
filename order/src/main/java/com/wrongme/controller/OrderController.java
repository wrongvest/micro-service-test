package com.wrongme.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "order1")
    public String order1() {
        return "order---->1";
    }

    @RequestMapping(value = "order2")
    public String order2() {
        return restTemplate.getForObject("http://user/user2", String.class);
    }
}
