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
        return "redirect:/list";  // 다른 url로 넘김
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", users);  // model에 data 저장해서 view(list.html)로 전달
        return "list";  // templates/list.html을 호출 -> .hml은 Spring이 자동으로 붙인다
    }
}
