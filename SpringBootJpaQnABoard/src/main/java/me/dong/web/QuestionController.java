package me.dong.web;

import me.dong.domain.Question;
import me.dong.domain.QuestionRepository;
import me.dong.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Dong on 2017-02-02.
 */
@Configuration
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * Q&A 화면으로 연결
     *
     * @return view path
     */
    @GetMapping("/qna_form")
    public String questionForm(HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/sign_in_form";
        }

        return "qna/qna_form";
    }

    /**
     * 로그인O -> 질문 등록 후 홈으로 이동
     * 로그인X -> 로그인 화면으로 이동
     *
     * @param session  세션
     * @param title    제목
     * @param contents 내용
     * @return redirection 시킬 url
     */
    @PostMapping("")
    public String create(HttpSession session, String title, String contents) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/sign_in_form";
        }
        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
        questionRepository.save(newQuestion);
        return "redirect:/";
    }
}
