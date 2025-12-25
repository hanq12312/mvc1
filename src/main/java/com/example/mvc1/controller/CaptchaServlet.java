package com.example.mvc1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应类型为图片，禁用缓存
        resp.setContentType("image/jpeg");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        // 生成4位验证码
        String captcha = generateCaptcha(4);
        // 存入Session
        HttpSession session = req.getSession();
        session.setAttribute("captcha", captcha);

        // 绘制验证码图片
        int width = 120, height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // 填充背景
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 0, width, height);

        // 绘制干扰线
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(
                    random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height)
            );
        }

        // 绘制验证码文字
        g.setColor(new Color(50, 50, 200));
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(captcha, 20, 28);
        g.dispose();

        // 输出图片
        javax.imageio.ImageIO.write(image, "jpeg", resp.getOutputStream());
    }

    // 生成随机验证码
    private String generateCaptcha(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}