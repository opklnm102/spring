package me.dong.web;

import me.dong.domain.Question;
import me.dong.domain.QuestionRepository;
import me.dong.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * 질문 등록 화면으로 연결
     *
     * @return view path
     */
    @GetMapping("/qna_form")
    public String questionForm(HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "/user/sign_in_form";
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
            return "/user/sign_in_form";
        }
        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        Question newQuestion = new Question(sessionUser, title, contents);
        questionRepository.save(newQuestion);
        return "redirect:/";
    }

    /**
     * 질문 상세보기 화면으로 이동
     *
     * @param model      전달할 질문 정보
     * @param questionId 질문 id
     * @return view path
     */
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Long questionId) {
        Question question = questionRepository.findOne(questionId);
        model.addAttribute("question", question);
        return "/qna/show";
    }

    /**
     * 질문 수정 화면으로 이동
     *
     * @param model      전달할 질문 정보
     * @param questionId 질문 id
     * @return view path
     */
    @GetMapping("/{id}/form")
    public String updateForm(Model model, HttpSession session, @PathVariable("id") Long questionId) {
        Question question = questionRepository.findOne(questionId);
        Result result = valid(session, question);
        if (!result.isValid()) {
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/sign_in_form";
        }
        model.addAttribute("question", question);
        return "/qna/update_form";
    }

    private Result valid(HttpSession session, Question question) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return Result.fail("로그인이 필요합니다.");
        }
        User loginUser = HttpSessionUtils.getUserFromSession(session);
        if (!question.matchWriter(loginUser)) {
            return Result.fail("자신이 쓴 글만 수정, 삭제 가능합니다.");
        }
        return Result.ok();
    }

//    private boolean hasPermission(HttpSession session, Question question) {
//        if (!HttpSessionUtils.isLoginUser(session)) {
//            throw new IllegalStateException("로그인이 필요합니다.");
//        }
//        User loginUser = HttpSessionUtils.getUserFromSession(session);
//        if (!question.matchWriter(loginUser)) {
//            throw new IllegalStateException("자신이 쓴 글만 수정, 삭제 가능합니다.");
//        }
//        return true;
//    }

    /**
     * 질문 정보 수정 후 질문 상세보기 화면으로 이동
     *
     * @param questionId 질문 id
     * @param title      제목
     * @param contents   내용
     * @return redirection 시킬 url
     */
    @PutMapping("/{id}")
    public String update(HttpSession session, Model model, @PathVariable("id") Long questionId, String title, String contents) {
        Question question = questionRepository.findOne(questionId);
        Result result = valid(session, question);
        if (!result.isValid()) {
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/sign_in_form";
        }
        question.update(title, contents);
        questionRepository.save(question);
        return String.format("redirect:/questions/%d", questionId);
    }

    /**
     * 질문 삭제 후 홈으로 이동
     *
     * @param questionId 질문 id
     * @return redirection 시킬 url
     */
    @DeleteMapping("/{id}")
    public String delete(HttpSession session, Model model, @PathVariable("id") Long questionId) {
        Question question = questionRepository.findOne(questionId);
        Result result = valid(session, question);
        if (!result.isValid()) {
            model.addAttribute("errorMessage", result.getErrorMessage());
            return "/user/sign_in_form";
        }
        questionRepository.delete(questionId);
        return "redirect:/";
    }
}