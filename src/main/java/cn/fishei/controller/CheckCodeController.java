package cn.fishei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller

/**
 * 验证码的绘制
 */
public class CheckCodeController {

    @RequestMapping("/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");


        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);


        Graphics g = image.getGraphics();
        //设置画笔颜色
        g.setColor(new Color((float)0.3,(float)0.3,(float)0.3,(float)1));
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("createCode",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.PINK);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",response.getOutputStream());
    }
    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

}
