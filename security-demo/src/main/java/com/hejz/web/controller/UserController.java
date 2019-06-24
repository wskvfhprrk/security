package com.hejz.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hejz.dto.User;
import com.hejz.exception.ResultException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/16 20:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
//        return authentication;
//        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping
    @JsonView(User.UserView.class)
//    public List<User> getUser(@RequestParam String username) {
    public List<User> getUser(User user, SpringDataWebProperties.Pageable pageable) {
//        System.out.println("username=====>" + username);
        System.out.println(ReflectionToStringBuilder.toString(user));
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getDefaultPageSize());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    //正则表达式
    @GetMapping("{id:\\d+}")
    @JsonView(User.UserInfo.class)
    public User getInfo(@PathVariable("id") String id) {
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    //正则表达式
    @PutMapping()
    @JsonView(User.UserInfo.class)
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    @JsonView(User.UserView.class)
    public User saveUser(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        user.setId(1);
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }

    @DeleteMapping("{id:\\d+}")
    public void delete(@PathVariable("id") String id) {
        System.out.println(id);
//        throw new ResultException(500, "错误信息", null, false);
    }
}
