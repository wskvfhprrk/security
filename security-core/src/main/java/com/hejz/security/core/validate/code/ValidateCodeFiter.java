package com.hejz.security.core.validate.code;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: hejz
 * @Description: OncePerRequestFilter这个过滤器保证每次只会被调用一次
 * @Date: 2019/6/25 21:24
 */
@Data
public class ValidateCodeFiter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //只有登陆还是post请求时才进入过滤器，
        if (StringUtils.equals("/user/login", request.getRequestURI())
                && StringUtils.endsWithIgnoreCase(request.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                //认证抛异常之后不能通过此过滤器
                return;
            }
        }
        //无论是其它路径还是验让码验试之后者于过此过滤器，但认证抛异常之后不能过此过滤器
        filterChain.doFilter(request, response);
    }

    //
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        //获取session中的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        //页面传过来的验证码
        String codeInHtml = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInHtml))
            throw new ValidateCodeException("验证码的值不能为空");
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已经过期");
        }
        if (!StringUtils.endsWithIgnoreCase(codeInSession.getCode(), codeInHtml))
            throw new ValidateCodeException("验证码不匹配");
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }
}
