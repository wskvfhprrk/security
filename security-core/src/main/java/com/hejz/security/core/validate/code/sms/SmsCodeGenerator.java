package com.hejz.security.core.validate.code.sms;

import com.hejz.security.core.properties.SecurityProperties;
import com.hejz.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/28 8:00
 */
@Data
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getCodeCount());
        ValidateCode validateCode = new ValidateCode(code, securityProperties.getCode().getImage().getExpireIn());
        return validateCode;
    }

}
