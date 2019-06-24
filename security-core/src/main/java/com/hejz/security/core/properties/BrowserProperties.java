package com.hejz.security.core.properties;

import lombok.Data;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/22 23:00
 */
@Data
public class BrowserProperties {
    private String loginPage = "/signIn.html";
    private LoginType loginType = LoginType.JSON;
}
