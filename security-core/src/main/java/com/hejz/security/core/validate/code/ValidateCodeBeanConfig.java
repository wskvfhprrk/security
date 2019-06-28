package com.hejz.security.core.validate.code;

import com.hejz.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/28 8:12
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 如果找不到名字叫imageCodeGenerator这个bean，就加载这个bean,找到了就不加载了
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenertor imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator=new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }
}
