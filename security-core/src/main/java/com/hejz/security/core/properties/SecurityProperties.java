package com.hejz.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/22 23:00
 */
@Configuration
@ConfigurationProperties(prefix = "com.hejz")
@Data
public class SecurityProperties {

    BrowserProperties browser = new BrowserProperties();
}
