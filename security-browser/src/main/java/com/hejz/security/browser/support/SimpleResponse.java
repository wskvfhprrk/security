package com.hejz.security.browser.support;

import lombok.Data;

/**
 * @Auther: hejz
 * @Description: 返回值
 * @Date: 2019/6/22 22:53
 */
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
