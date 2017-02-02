package me.dong.web;

import me.dong.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Dong on 2017-02-01.
 */
@Controller
public class HomeController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }
}
