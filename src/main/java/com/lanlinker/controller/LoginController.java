package com.lanlinker.controller;

import com.lanlinker.domain.Admin;
import com.lanlinker.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 后台登录页控制器
 */
@Controller
@RequestMapping("/background")
public class LoginController {
    /**
     * 注入管理员业务操作对象
     */
    @Autowired
    private AdminService adminService;
    /**
     * 请求后台登录页
     * @return 后台登录页面
     */
    @GetMapping("/login")
    public String login(ModelMap map){
        Admin admin = new Admin();
        admin.setUsername("");
        admin.setPassword("");
        map.addAttribute("admin",admin);
        return "login";
    }
    @PostMapping("/loginSystem")
    public String loginSystem(@Valid Admin admin, BindingResult result, ModelMap map, HttpSession session){
        if(result.hasErrors()){
            map.addAttribute("msg",result.getFieldErrors().get(0).getDefaultMessage());
            map.addAttribute("admin",admin);
            return "login";
        }
        Admin _admin = adminService.login(admin);
        if (_admin!=null){
            session.setAttribute("admin",_admin);
            return "redirect:/background/starter";
        }else {
            map.addAttribute("msg","用户名或者密码错误");
            map.addAttribute("admin",admin);
            return "login";
        }
    }
}
