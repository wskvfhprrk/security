package com.hejz.security.core.validate.code;

import com.hejz.security.core.validate.code.image.ImageCode;
import com.hejz.security.core.validate.code.sms.SmsCodeSender;
import com.hejz.security.core.validate.code.sms.ValidateCode;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
    private ValidateCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

//    @GetMapping("/code/image")
//    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//        ImageIO.write(imageCode.getImage(), "PNG", response.getOutputStream());
//    }
//
//    @GetMapping("/code/sms")
//    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//        //ServletRequestUtils.getRequiredStringParameter是请求中必须含有某个参数
//        //ServletRequestUtils.getStringParameter是请求中含有某个参数（不是必须的）
//        String mobile= ServletRequestUtils.getRequiredStringParameter(request,"mobile");
//        smsCodeSender.send(mobile,smsCode.getCode());
//    }

    @GetMapping("/code/{type}")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
    }

}
