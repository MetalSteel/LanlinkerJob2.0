package com.lanlinker.controller;

import com.lanlinker.domain.Email;
import com.lanlinker.domain.Msg;
import com.lanlinker.repository.EmailRepository;
import com.lanlinker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Email设置控制器
 */
@Controller
@RequestMapping("/background")
public class EmailController {
    /**
     * 注入邮箱业务层操作接口
     */
    @Autowired
    private EmailService emailService;
    /**
     * 邮箱页面
     */
    @GetMapping("/email")
    public String email(){
        return "function/email";
    }
    /**
     * 邮箱信息回显
     * @return
     */
    @GetMapping("/findEmail")
    @ResponseBody
    public Email findEmail(){
        return emailService.findEmail();
    }
    /**
     * 邮箱信息更新
     * @param email
     * @return
     */
    @RequestMapping("/updateEmail")
    @ResponseBody
    public Msg updateEmail(Email email){
        emailService.updateEmail(email);
        return new Msg("OK","邮箱信息更新成功");
    }
}
