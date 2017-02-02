package me.dong.web;

import me.dong.domain.User;
import me.dong.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Dong on 2017-01-31.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired  // SpringBoot가 알아서 생성해놓은것을 가져다 쓴다
    private UserRepository userRepository;

    /**
     * 회원가입 화면으로 연결
     *
     * @return view path
     */

    @GetMapping("/sign_up_form")
    public String signUpForm() {
        return "user/sign_up_form";
    }

    /**
     * 회원가입 후 회원목록 화면으로 이동
     *
     * @param user 회원가입할 회원정보
     * @return redirection 시킬 url
     */
    @PostMapping
    public String create(User user) {
        System.out.println("user: " + user);
        userRepository.save(user);
        return "redirect:/users";  // 다른 url로 넘김
    }

    /**
     * 로그인 화면으로 연결
     *
     * @return view path
     */
    @GetMapping("/sign_in_form")
    public String signInForm() {
        return "user/sign_in_form";
    }

    @PostMapping("/sign_in")
    public String signIn(HttpSession session, String userId, String password){
        User user = userRepository.findByUserId(userId);
        if(user == null){
            System.out.println("Login Failure!");
            return "redirect:/users/sign_in_form";
        }
        if(!password.equals(user.getPassword())){
            System.out.println("Login Failure!");
            return "redirect:/users/sign_in_form";
        }
        System.out.println("Login Success!");
        session.setAttribute("user", user);  // 세션에 정보 저장

        return "redirect:/";
    }

    /**
     * 프로필 화면으로 연결
     *
     * @return view path
     */
    @GetMapping("/profile")
    public String profile() {
        return "user/profile";
    }

    /**
     * 회원정보 수정 화면으로 이동
     *
     * @param model  전달할 회원정보
     * @param userId 회원 id
     * @return view path
     */
    @GetMapping("/{id}/form")
    public String updateForm(Model model, @PathVariable(name = "id") Long userId) {
        User user = userRepository.findOne(userId);
        model.addAttribute("user", user);
        return "/user/update_form";
    }

    /**
     * 회원정보 수정 후 회원목록 화면으로 이동
     *
     * @param userId  회원 id
     * @param newUser 수정할 회원정보
     * @return redirection 시킬 url
     */
    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long userId, User newUser) {
        User user = userRepository.findOne(userId);
        user.update(newUser);
        userRepository.save(user);
        return "redirect:/users";
    }

    /**
     * 회원목록 쿼리 후 회원목록 화면으로 연결
     *
     * @param model
     * @return
     */
    @GetMapping
    public String list(Model model) {
        // model에 data 저장해서 view(/template/user/list.html)로 전달
        model.addAttribute("users", userRepository.findAll());
        return "user/list";  // templates/user/list.html을 호출 -> .html은 Spring이 자동으로 붙인다
    }
}