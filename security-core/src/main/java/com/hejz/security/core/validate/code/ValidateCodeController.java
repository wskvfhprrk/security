package com.hejz.security.core.validate.code;

import com.hejz.security.core.properties.ImageCodeProperties;
import com.hejz.security.core.properties.SecurityProperties;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/25 8:41
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    //操作session
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/code/images")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCodeProperties image = securityProperties.getCode().getImage();
        CreateImageCode createImageCode = new CreateImageCode(
                ServletRequestUtils.getIntParameter(request,"width",image.getWidth()),
                ServletRequestUtils.getIntParameter(request,"height",image.getHeight()),
                ServletRequestUtils.getIntParameter(request,"codeCount",image.getCodeCount()),
                ServletRequestUtils.getIntParameter(request,"lineCount",image.getLineCount()));
        BufferedImage buffImg = createImageCode.getBuffImg();
        String code = createImageCode.getCode();
        ImageCode imageCode = new ImageCode(buffImg, code, ServletRequestUtils.getIntParameter(request,"expireIn",image.getExpireIn()));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "PNG", response.getOutputStream());
    }




}
