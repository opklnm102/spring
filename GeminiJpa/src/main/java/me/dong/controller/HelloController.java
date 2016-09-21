package me.dong.controller;

import me.dong.domain.user.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(@RequestBody User user){
        return "Hello world " + user.toString();
    }
}
