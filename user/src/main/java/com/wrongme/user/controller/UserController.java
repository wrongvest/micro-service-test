package com.wrongme.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/user1")
    public String order1() {
        return "user---->1";
    }

    @RequestMapping(value = "/user2")
    public String order2() {
        return "user2---->order2--->";
    }
}
