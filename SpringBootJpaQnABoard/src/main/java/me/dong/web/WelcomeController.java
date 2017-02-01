package me.dong.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Dong on 2017-01-31.
 */
@Controller
public class WelcomeController {

    @GetMapping("/helloworld")
    public String welcome(Model model, String name, int age){
        System.out.println("name: " + name + " age: " + age);
        model.addAttribute("name", name);  // model에 data 저장해서 view(welcome.html)로 전달
        model.addAttribute("age", age);
        return "welcome";  // templates/welcome.html을 호출 -> .hml은 Spring이 자동으로 붙인다
    }
}
