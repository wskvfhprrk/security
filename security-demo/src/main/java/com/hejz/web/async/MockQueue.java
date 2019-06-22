package com.hejz.web.async;

import org.springframework.stereotype.Component;

/**
 * @Auther: hejz
 * @Description: 虚拟队列
 * @Date: 2019/6/22 11:37
 */
@Component
public class MockQueue {

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            System.out.println("接到下单请求," + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            System.out.println("下单请求处理完毕，" + placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
