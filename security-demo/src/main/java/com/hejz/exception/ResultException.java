package com.hejz.exception;

import lombok.Data;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/18 7:55
 */
@Data
public class ResultException extends RuntimeException {
    private Integer code;
    private String msg;
    private Object content;
    private Boolean success;

    public ResultException(Integer code, String msg, Object content, Boolean success) {
        this.code = code;
        this.msg = msg;
        this.content = content;
        this.success = success;
    }

//    public ResultException(String message, Integer code, String msg, Object content, Boolean success) {
//        super(message);
//        this.code = code;
//        this.msg = msg;
//        this.content = content;
//        this.success = success;
//    }
//
//    public ResultException(String message, Throwable cause, Integer code, String msg, Object content, Boolean success) {
//        super(message, cause);
//        this.code = code;
//        this.msg = msg;
//        this.content = content;
//        this.success = success;
//    }
//
//    public ResultException(Throwable cause, Integer code, String msg, Object content, Boolean success) {
//        super(cause);
//        this.code = code;
//        this.msg = msg;
//        this.content = content;
//        this.success = success;
//    }
//
//    public ResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String msg, Object content, Boolean success) {
//        super(message, cause, enableSuppression, writableStackTrace);
//        this.code = code;
//        this.msg = msg;
//        this.content = content;
//        this.success = success;
//    }
}
