package com.hejz.security.browser;

import com.hejz.security.browser.support.SimpleResponse;
import com.hejz.security.core.properties.LoginType;
import com.hejz.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/22 22:39
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    private RequestCache requestCache=new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthention(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest=requestCache.getRequest(request,response);
        if(savedRequest!=null){
            String redirectUrl=savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：{}",redirectUrl);
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
//                securityProperties.getBrowser().setLoginType(LoginType.REDIRECT);
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }else {
//                securityProperties.getBrowser().setLoginType(LoginType.JSON);
                return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
            }
       }
       return null;
    }
}
