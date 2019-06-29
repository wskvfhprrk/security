package com.hejz.security.core.properties;

import lombok.Data;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/26 8:22
 */
@Data
public class SmsCodeProperties {
    private int codeCount = 6;
    private int expireIn = 60;

    private String url;

}
