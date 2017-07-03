package com.lanlinker.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码工具类
 */
public class VerifyCode {
    // 种子数据
    private static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    // 种子字体颜色
    private static Color[] fontcolors = {Color.BLACK,Color.RED,Color.CYAN,Color.YELLOW,Color.BLUE};
    // 随机数生成对象
    private static Random random = new Random();
    // 验证码
    private static String realCode;
    // 生成验证码图片
    public static void generateVerifyImage(OutputStream os) throws IOException {
        // 验证码
        StringBuilder verifyCode = new StringBuilder();
        // 创建图片缓冲区
        BufferedImage verifyImage = new BufferedImage(103, 32, BufferedImage.TYPE_INT_RGB);
        // 创建画笔
        Graphics g = verifyImage.getGraphics();
        // 设置背景颜色
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,103,32);
        // 设置画笔大小
        String[] fonts = { "仿宋_GB2312", "方正舒体", "黑体", "华文彩云", "华文彩云", "隶书",
                "宋体", "华文新魏", "微软雅黑", "幼圆,Arial", "Calibri", "Dotum",
                "Algerian", "Broadway", "Curlz MT", "Brush Script MT",
                "Elephant" };
        // 画随机数据
        for (int i = 1; i <= 4; i++) {
            // 生成随机数据
            int rNum = random.nextInt(32);
            char rChar = VERIFY_CODES.charAt(rNum);
            // 设置随机字体
            int rFont = random.nextInt(17);
            Font font = new Font(fonts[rFont], Font.ITALIC, 22);
            g.setFont(font);
            // 生成随机颜色
            g.setColor(fontcolors[random.nextInt(5)]);
            // 画字符串
            g.drawString(rChar+"",i*18+i,20+random.nextInt(5));
            // 拼装
            verifyCode.append(rChar);
        }
        // 验证码
        realCode = verifyCode.toString();
        // 输出图片
        ImageIO.write(verifyImage,"JPEG",os);
    }
    // 返回验证码数据
    public static String getVerifyCode(){
        return realCode;
    }
}
