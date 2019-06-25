package com.hejz.security.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/25 8:37
 */
@Data
public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }


//    public static void main(String[] args) {
//        //获取当前时间
//        LocalDateTime nowTime = LocalDateTime.now();
//        //自定义时间
//        LocalDateTime endTime = LocalDateTime.of(2017, 10, 22, 10, 10, 10);
//        //比较  现在的时间 比 设定的时间 之前  返回的类型是Boolean类型
//        System.out.println(nowTime.isAfter(endTime));
//        //比较   现在的时间 比 设定的时间 之后  返回的类型是Boolean类型
//        System.out.println(nowTime.isBefore(endTime));
//        //比较   现在的时间 和 设定的时候  相等  返回类型是Boolean类型
//        System.out.println(nowTime.equals(endTime));
//    }
}
