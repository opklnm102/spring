package me.dong.exception;

import me.dong.model.vo.ResponseVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 예외 발생시 처리할 핸들러 클래스
 */
@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseVO baseException(BaseException ex, HttpServletRequest request){
        return new ResponseVO(ex.getResultCode(), ex.getMessage());
    }
}
