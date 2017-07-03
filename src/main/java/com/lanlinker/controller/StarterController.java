package com.lanlinker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台主页控制器
 */
@Controller
@RequestMapping("/background")
public class StarterController {
    /**
     * 后台主页
     * @return 后台主页
     */
    @GetMapping("/starter")
    public String starter(){
        return "starter";
    }
}
