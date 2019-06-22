package com.hejz.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/22 11:23
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("order")
    public DeferredResult<String> order()  {
        log.info("主线程开始");

        String s = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(s);

        DeferredResult<String> result=new DeferredResult<>();
        deferredResultHolder.getMap().put(s,result);

      /*  Callable<String> result = () -> {
            log.info("副线程开始");
            Thread.sleep(1000);
            log.info("副线程结束");
            return "success";
        };*/
        log.info("主线程结束");
        return result;
    }
}
