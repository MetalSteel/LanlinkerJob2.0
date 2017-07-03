package com.lanlinker.service;

import com.lanlinker.domain.Email;
import com.lanlinker.domain.Msg;
import com.lanlinker.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Email业务层操作接口
 */
@Service
public class EmailService {
    /**
     * 注入Email持久层操作接口
     */
    @Autowired
    private EmailRepository emailRepository;
    /**
     * 邮件发送接口
     */
    private JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
    /**
     * 修改Email
     */
    public void updateEmail(Email email){
        // 默认更新第一个Email
        email.setId(1);
        emailRepository.save(email);
    }
    /**
     * 查找Email(id=1)
     */
    public Email findEmail(){
        return emailRepository.findOne(1);
    }
    /**
     * 发送普通邮件
     */
    public Msg sendSimpleMailMessage(String to,String name,String content){
        // 配置信息
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.auth", "true");
        // 设置配置信息
        javaMailSenderImpl.setJavaMailProperties(properties);
        // 查找Email配置
        Email email = findEmail();
        // 设置发件人信息
        javaMailSenderImpl.setHost(email.getHost());
        javaMailSenderImpl.setUsername(email.getUsername());
        javaMailSenderImpl.setPassword(email.getPassword());
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        // 创建消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getUsername());
        message.setTo(to);
        message.setSubject(email.getSubject());
        // 拼接Content
        content = email.getStart()+","+name+"\r\n"+content+email.getEnd();
        message.setText(content);
        // 发送消息
        javaMailSenderImpl.send(message);

        return new Msg("OK","邮件发送成功~");
    }
}
