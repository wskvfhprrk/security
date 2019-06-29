package com.hejz.code;

import com.hejz.security.core.validate.code.image.ImageCode;
import com.hejz.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/28 8:34
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码");
        return null;
    }
}
