package com.hejz.security.core.validate.code.sms;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/29 15:59
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}
