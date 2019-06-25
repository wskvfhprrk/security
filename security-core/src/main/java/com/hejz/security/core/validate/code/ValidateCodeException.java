package com.hejz.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/25 21:30
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
