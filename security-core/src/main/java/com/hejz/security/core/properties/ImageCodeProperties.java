package com.hejz.security.core.properties;

import lombok.Data;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/26 8:22
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setCodeCount(4);
    }

    private int width = 67;
    private int height = 40;
    private int lineCount = 20;
}
