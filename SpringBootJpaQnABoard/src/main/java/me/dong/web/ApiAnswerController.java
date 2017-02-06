package me.dong.web;

import me.dong.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Dong on 2017-02-03.
 */
@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("")
    public Answer create(HttpSession session, @PathVariable("questionId") Long questionId, String contents) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return null;
        }
        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Question question = questionRepository.findOne(questionId);
        Answer answer = new Answer(loginUser, question, contents);
        question.addAnswer();  //Todo: 이건 왜 정상동작하는지 알아보기
        return answerRepository.save(answer);
    }

    @DeleteMapping("/{id}")
    public Result delete(HttpSession session, @PathVariable("questionId") Long questionId, @PathVariable("id") Long answerId) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return Result.fail("로그인해야 합니다.");
        }
        Answer answer = answerRepository.findOne(answerId);
        User loginUser = HttpSessionUtils.getUserFromSession(session);
        if (!answer.matchWriter(loginUser)) {
            return Result.fail("자신의 글만 삭제할 수 있습니다.");
        }

        answerRepository.delete(answerId);

        Question question = questionRepository.findOne(questionId);
        question.deleteAnswer();
        questionRepository.save(question);
        return Result.ok();
    }
}
