package com.hejz.security.core.properties;

import lombok.Data;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/26 8:25
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image=new ImageCodeProperties();
}
