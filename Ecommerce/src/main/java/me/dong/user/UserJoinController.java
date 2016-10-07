package me.dong.user;

import me.dong.model.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/join")
public class UserJoinController {

    @RequestMapping(method = RequestMethod.GET)
    public String join(User user){
        return "/user/join-form";
    }
}
