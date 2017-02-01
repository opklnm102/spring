package me.dong.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2017-01-31.
 */
@Controller
public class UserController {

    private List<User> users = new ArrayList<>();

    @PostMapping("/create")
    public String create(User user) {
        System.out.println("user: " + user);
        users.add(user);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", users);
        return "list";
    }
}
