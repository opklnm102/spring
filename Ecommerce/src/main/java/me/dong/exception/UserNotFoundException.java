package me.dong.exception;

import me.dong.model.vo.ResultCodes;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        this(null);
    }

    public UserNotFoundException(String message){
        super(ResultCodes.USER_NOT_FOUND, message);
    }
}
