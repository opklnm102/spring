package me.dong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Dong on 2017-02-01.
 */
@Controller
public class HomeController {

    @GetMapping("")
    public String home(){
        return "index";
    }
}
