package com.hejz.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenertor {

    ImageCode generate(ServletWebRequest request);
}
