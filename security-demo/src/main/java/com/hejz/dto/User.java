package com.hejz.dto;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/16 20:58
 */
public class User {

    public interface UserView {
    }

    public interface UserInfo extends UserView {
    }

    private Integer id;
    private String username;
    @NotBlank(message = "password不能为空")
    private String password;
    private Integer age;

//    @Past(message = "必须是已经过去的时间")
    private Date birthday;

    @JsonView(UserView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(UserView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserInfo.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserView.class)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
