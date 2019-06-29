package com.hejz.security.core.validate.code;

import com.hejz.security.core.validate.code.sms.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
