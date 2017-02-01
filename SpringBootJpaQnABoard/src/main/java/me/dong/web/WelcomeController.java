package me.dong.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2017-01-31.
 */
@Controller
public class WelcomeController {

    @GetMapping("/helloworld")
    public String welcome(Model model){
        model.addAttribute("name", "dong");
        model.addAttribute("age", 30);
        model.addAttribute("texted_value", 1000);
        model.addAttribute("text_html", "<b>1000</b>");
        model.addAttribute("is_age", true);

        List<User> users = new ArrayList<>();
        User user;
        for(int i=0; i<10; i++){
            user = new User();
            user.setName("dong" + i);
            users.add(user);
        }
        model.addAttribute("users", users);
        return "welcome";
    }
}
