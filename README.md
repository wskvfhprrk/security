# spring-security

## springSecurity开发基于表单的认证

### 自定义用户认证逻辑

* 处理用户信息获取逻辑    `UserDetailsService`
* 处理用户校验逻辑         `UserDetails`
* 处理密码加密解密         `PasswordEncoder` 

#### 步骤
1、实现`UserDetailsService`——`MyUserDetailsService`,并使用注解`@Configuration`；

2、在实现类`MyUserDetailsService`中添加一个`@Bean`；

3、处理加解密使用`PasswordEncoder`。
```java
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
```
### 个性化用户认证流程

* 自定义登录页面  `http.formLogin().loginPage("/signIn.html)`
* 自定义登录成功处理——实现`AuthenticationSuccessHandler`接口
* 自定义登录失败处理——实现`AuthenticationFailureHandler`接口

### 实现图形验证码功能

* 开发生成图形验证码接口
    * 根据随机数生成图片
    * 将随机数存到session中
    * 在将生成的图片写到接口响应中
* 在认证流程中加入图形验证码校验
* 生构代码