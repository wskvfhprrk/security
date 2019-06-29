package com.hejz.security.core.validate.code.impl;

import com.hejz.security.core.validate.code.ValidateCodeException;
import com.hejz.security.core.validate.code.ValidateCodeGenerator;
import com.hejz.security.core.validate.code.ValidateCodeProcessor;
import com.hejz.security.core.validate.code.sms.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/29 16:28
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

//    @Autowired
//    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 生成校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getValidateCodeType(request).toUpperCase(), validateCode);
    }

    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toLowerCase();
        String generatorName = type + "CodeGenerator";
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    private String getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
        return type;
    }
}
