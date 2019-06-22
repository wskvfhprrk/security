package com.hejz.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/18 8:00
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map<String,Object> map=new HashMap<>();
       if(ex instanceof ResultException){
           ResultException ex1 = (ResultException) ex;
           map.put("success",ex1.getSuccess());
           map.put("code",ex1.getCode());
           map.put("content",ex1.getContent());
           map.put("massage",ex1.getMsg());
       }
        return map;
    }
}
