package me.dong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 로그인 후 이동할 /home 요청을 처리할 컨트롤러
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RedirectView main(){
        return new RedirectView("/home");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }
}
