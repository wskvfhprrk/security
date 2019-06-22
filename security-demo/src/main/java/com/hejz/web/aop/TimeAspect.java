package com.hejz.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/18 21:06
 */
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.hejz.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("aspect time begin");
        long begin = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg=="+arg);
        }

        Object object = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("aspect 耗时：" + (end - begin));
        System.out.println("aspect time end");
        return object;
    }
}
