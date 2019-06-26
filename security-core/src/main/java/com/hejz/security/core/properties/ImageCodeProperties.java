package com.hejz.security.core.properties;

import lombok.Data;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/26 8:22
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 40;
    private int codeCount = 4;
    private int lineCount = 20;
    private int expireIn = 60;

    private String url="/user/login";

}
