package com.lanlinker.controller;

import com.lanlinker.domain.Msg;
import com.lanlinker.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Properties;

/**
 * 系统首页
 */
@Controller
@RequestMapping("/background")
public class SystemController {
    /**
     * 注入简历业务操作接口
     */
    @Autowired
    private ResumeService resumeService;
    /**
     * 访问后台首页
     * @return
     */
    @GetMapping("/system")
    public String system(){
        return "function/system";
    }
    /**
     * 查询所有简历数量
     * @return
     */
    @GetMapping("/countAllResumes")
    @ResponseBody
    private Msg countAllResumes(){
        Integer allResumes = resumeService.countAllResumes();
        return new Msg("OK",allResumes+"");
    }
    /**
     * 查询当天新增简历数量
     * @return
     * @throws ParseException
     */
    @GetMapping("/countTodayResumes")
    @ResponseBody
    private Msg countTodayResumes() throws ParseException {
        Integer dayResumes = resumeService.countTodayResumes();
        return new Msg("OK",dayResumes+"");
    }
}
