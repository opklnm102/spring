package me.dong.web;

import me.dong.domain.User;
import me.dong.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Dong on 2017-01-31.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired  // SpringBoot가 알아서 생성해놓은것을 가져다 쓴다
    private UserRepository userRepository;

    @GetMapping("/sign_up")
    public String signUpForm() {
        return "user/sign_up_form";
    }

    @GetMapping("/sign_in")
    public String signInForm() {
        return "user/sign_in_form";
    }

    @GetMapping("/profile")
    public String profile() {
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String updateForm(Model model, @PathVariable(name = "id") Long userId) {
        User user = userRepository.findOne(userId);
        model.addAttribute("user", user);
        return "/user/update_form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long userId, User newUser) {
        User user = userRepository.findOne(userId);
        user.update(newUser);
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping
    public String create(User user) {
        System.out.println("user: " + user);
        userRepository.save(user);
        return "redirect:/users";  // 다른 url로 넘김
    }

    @GetMapping
    public String list(Model model) {
        // model에 data 저장해서 view(/template/user/list.html)로 전달
        model.addAttribute("users", userRepository.findAll());
        return "user/list";  // templates/user/list.html을 호출 -> .html은 Spring이 자동으로 붙인다
    }
}