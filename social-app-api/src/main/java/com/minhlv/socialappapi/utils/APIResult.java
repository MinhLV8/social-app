package com.minhlv.socialappapi.utils;

import java.util.ArrayList;

public class APIResult {

    public interface ERROR_CODE {
        int EMPTY_DATA = 1;
        int PARAM_INVALID = 2;
        int CHILD_EXISTS = 3;
        int INSERT_AUTH = 4;
        int SELECT_AUTH = 5;
        int NOT_EXISTS = 6;
        int REF_ILLEGAL = 7;
        int DATABASE_ERROR = 8;
        int DOITUONG_ILLEGAL = 9;
        int EXISTS = 10;
        int NOT_PERMISSION = 11;
        int UNKNOW_ERROR = 99;
    }

    public enum MSG {
        SUCCESS, UNEXPECTED_ERROR_OCCURRED, NOT_PERMISSION, NOT_EXISTS, ACCESS_DENIED, ACTION_FORBIDDEN;

        public String getMSG() {

            switch (this) {
                case SUCCESS :
                    return "Success";
                case UNEXPECTED_ERROR_OCCURRED :
                    return "Unexpected error occurred";
                case NOT_PERMISSION :
                    return "Not permission";
                case NOT_EXISTS :
                    return "Not exists";
                case ACCESS_DENIED :
                    return "Access denied";
                case ACTION_FORBIDDEN :
                    return "Action forbidden";
                default :
                    return null;
            }
        }
    }

    private int errorCode = 0;
    private String message = "";
    private Object data = null;
    private long timeStart = 0;
    private long timeEnd = 0;
    private long timeTotal = 0;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        this.timeEnd = System.currentTimeMillis();
        this.timeTotal = timeEnd - timeStart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.timeEnd = System.currentTimeMillis();
        this.timeTotal = timeEnd - timeStart;
    }

    public void setMessage(Exception exeption) {
        this.timeEnd = System.currentTimeMillis();
        this.timeTotal = timeEnd - timeStart;
        this.message = exeption.getMessage();
        this.errorCode = ERROR_CODE.UNKNOW_ERROR;
        this.data = new ArrayList<>();
    }

    public void setMessage(int errorCode, MSG message) {
        this.timeEnd = System.currentTimeMillis();
        this.timeTotal = timeEnd - timeStart;
        this.message = message.getMSG();
        this.errorCode = errorCode;
        this.data = new ArrayList<>();
    }

    public void setMessage(int errorCode, String message) {
        this.timeEnd = System.currentTimeMillis();
        this.timeTotal = timeEnd - timeStart;
        this.message = message;
        this.errorCode = errorCode;
        this.data = new ArrayList<>();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        this.timeEnd = System.currentTimeMillis();
        this.timeTotal = timeEnd - timeStart;
    }

    public void setData(Object data, MSG message) {
        this.data = data;
        this.message = message.getMSG();
        this.timeEnd = System.currentTimeMillis();
        this.timeTotal = timeEnd - timeStart;
    }

    public APIResult() {
        this.timeStart = System.currentTimeMillis();
    }

    public long getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(long timeTotal) {
        this.timeTotal = timeTotal;
    }
}
