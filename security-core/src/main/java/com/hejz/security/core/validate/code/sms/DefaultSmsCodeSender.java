package com.hejz.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/29 16:00
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("向手机:{}==>发送验证码:{}", mobile, code);
    }
}
