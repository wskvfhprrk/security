package com.hejz.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/22 20:31
 */
@Component
@Slf4j
public class MyUserDatailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆用户名：{}",username);
        //根据用户名查找用户信息——从数据库中要读取出来的，passwordEncoder.encode方法是加密时使用的，这里应该只读取数据库的密码
        String password = passwordEncoder.encode("123456");
        log.info("密码为：{}",password);
        return new User(username, password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
    }
}
