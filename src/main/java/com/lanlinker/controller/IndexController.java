package com.lanlinker.controller;

import com.lanlinker.domain.Msg;
import com.lanlinker.domain.Resume;
import com.lanlinker.service.ResumeService;
import com.lanlinker.utils.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 简历录入首页控制器
 */
@Controller
public class IndexController {
    /**
     * 注入简历业务层操作对象
     */
    @Autowired
    private ResumeService resumeService;
    /**
     * 添加简历首页
     * @return 添加简历首页
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    /**
     * 生成验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("/getVerifyImage")
    public void  getVerifyImage(HttpServletResponse response, HttpSession session) throws IOException {
        // 生成验证码图片
        VerifyCode.generateVerifyImage(response.getOutputStream());
        // 将验证码保存到session中
        session.setAttribute("verifyCode", VerifyCode.getVerifyCode());
    }

    /**
     * 提交简历
     * @param resume
     * @param verifyCode
     * @param session
     * @return
     */
    @PostMapping("/submitResume")
    @ResponseBody
    public Msg submitResume(@Valid  Resume resume, BindingResult result, String verifyCode, HttpSession session){
        Msg msg = new Msg();
        // 从session中获取验证码
        String _verifyCode = (String) session.getAttribute("verifyCode");
        // 判断验证码是否正确,忽略大小写
        System.out.println(_verifyCode+"=="+verifyCode);
        if(!_verifyCode.equalsIgnoreCase(verifyCode)){
            // 回显验证码错误信息
            msg.setStatus("VerifyCodeError");
            msg.setMsg("验证码错误");
            return msg;
        }
        // 校验数据格式
        if(result.hasErrors()){
            msg.setStatus("ERROR");
            msg.setMsg(result.getFieldErrors().get(0).getDefaultMessage());
            return msg;
        }
        resumeService.addResume(resume);
        msg.setStatus("OK");
        msg.setMsg("添加简历成功!");

        return msg;
    }

}
