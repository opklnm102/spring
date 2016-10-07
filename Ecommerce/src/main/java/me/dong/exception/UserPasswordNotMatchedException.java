package me.dong.exception;

import me.dong.model.vo.ResultCodes;

public class UserPasswordNotMatchedException extends BaseException {


    public UserPasswordNotMatchedException() {
        this(null);
    }

    public UserPasswordNotMatchedException(String message){
        super(ResultCodes.USER_PASSWORD_NOT_MATCHED, message);
    }
}
