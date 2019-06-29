package com.hejz.security.core.validate.code.image;

import com.hejz.security.core.properties.ImageCodeProperties;
import com.hejz.security.core.properties.SecurityProperties;
import com.hejz.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/28 8:00
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        ImageCodeProperties image = securityProperties.getCode().getImage();
        image.setWidth(ServletRequestUtils.getIntParameter(request.getRequest(), "width", image.getWidth()));
        image.setHeight(ServletRequestUtils.getIntParameter(request.getRequest(), "height", image.getHeight()));
        image.setCodeCount(ServletRequestUtils.getIntParameter(request.getRequest(), "codeCount", image.getCodeCount()));
        image.setLineCount(ServletRequestUtils.getIntParameter(request.getRequest(), "lineCount", image.getLineCount()));
        image.setExpireIn(ServletRequestUtils.getIntParameter(request.getRequest(), "expireIn", image.getExpireIn()));
        createImage();
        ImageCode imageCode = new ImageCode(buffImg, code, securityProperties.getCode().getImage().getExpireIn());
        return imageCode;
    }


    /**
     * 验证码
     */
    private String code = null;

    private BufferedImage buffImg = null;
    Random random = new Random();

    /**
     * 生成图片
     */
    private void createImage() {
        // 字体的宽度
        int fontWidth = securityProperties.getCode().getImage().getWidth() / securityProperties.getCode().getImage().getCodeCount();
        // 字体的高度
        int fontHeight = securityProperties.getCode().getImage().getHeight() - 5;
        int codeY = securityProperties.getCode().getImage().getHeight() - 8;

        // 图像buffer
        buffImg = new BufferedImage(securityProperties.getCode().getImage().getWidth(), securityProperties.getCode().getImage().getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();

        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, securityProperties.getCode().getImage().getWidth(), securityProperties.getCode().getImage().getHeight());


        // 设置字体
        //Font font1 = getFont(fontHeight);
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < securityProperties.getCode().getImage().getLineCount(); i++) {
            int xs = random.nextInt(securityProperties.getCode().getImage().getWidth());
            int ys = random.nextInt(securityProperties.getCode().getImage().getHeight());
            int xe = xs + random.nextInt(securityProperties.getCode().getImage().getWidth());
            int ye = ys + random.nextInt(securityProperties.getCode().getImage().getHeight());
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点
        float yawpRate = 0.01f;
        int area = (int) (yawpRate * securityProperties.getCode().getImage().getWidth() * securityProperties.getCode().getImage().getHeight());
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(securityProperties.getCode().getImage().getWidth());
            int y = random.nextInt(securityProperties.getCode().getImage().getHeight());

            buffImg.setRGB(x, y, random.nextInt(255));
        }

        // 得到随机字符
        String str1 = randomStr(securityProperties.getCode().getImage().getCodeCount());
        this.code = str1;
        for (int i = 0; i < securityProperties.getCode().getImage().getCodeCount(); i++) {
            String strRand = str1.substring(i, i + 1);
            g.setColor(getRandColor(1, 255));

            // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
            g.drawString(strRand, i * fontWidth + 3, codeY);
        }
    }

    /**
     * 得到随机字符串
     *
     * @param n
     * @return
     */
    private String randomStr(int n) {
        String str1 = "ABCDEFGHJKMNOPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    /**
     * 得到随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 产生随机字体
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font[] font = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }

    /**
     * 扭曲方法
     *
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }


    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code.toLowerCase();
    }
}
