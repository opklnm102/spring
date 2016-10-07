package me.dong.exception;

import me.dong.model.vo.ResultCodes;

/**
 * Created by Dong on 2016-10-05.
 */
public class InvalidParameterException extends BaseException {

    public InvalidParameterException(){
        this(null);
    }

    public InvalidParameterException(String message) {
        super(ResultCodes.EMPTY_PARAMETER, message);
    }
}
