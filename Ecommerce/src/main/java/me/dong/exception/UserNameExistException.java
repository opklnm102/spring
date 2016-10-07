package me.dong.exception;

import me.dong.model.vo.ResultCodes;

public class UserNameExistException extends BaseException {

    public UserNameExistException(){
        this(null);
    }

    public UserNameExistException(String message){
        super(ResultCodes.USER_ALREADY_EXIST, message);
    }
}
