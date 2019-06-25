package com.hejz.security.browser;

import com.hejz.security.core.properties.SecurityProperties;
import com.hejz.security.core.validate.code.ValidateCodeFiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/22 19:58
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFiter validateCodeFiter=new ValidateCodeFiter();
        validateCodeFiter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        http
                .addFilterBefore(validateCodeFiter, UsernamePasswordAuthenticationFilter.class)  //添加验证码过滤器在UsernamePasswordAuthenticationFilter前面
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/user/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)

//        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/code/images",
                        securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
