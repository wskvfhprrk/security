package com.hejz.security.core.validate.code.image;

import com.hejz.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/29 16:39
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    /**
     * 发送图形验证码
     * @param request
     * @param imageCode
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getImage(),"png",request.getResponse().getOutputStream());
    }
}
