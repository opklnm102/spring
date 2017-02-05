package me.dong.web;

/**
 * Created by Dong on 2017-02-05.
 * Validation check 후 결과를 전달
 */
public class Result {

    private boolean valid;

    private String errorMessage;

    private Result(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public static Result ok() {
        return new Result(true, null);
    }

    public static Result fail(String errorMessage) {
        return new Result(false, errorMessage);
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
