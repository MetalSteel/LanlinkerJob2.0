package com.lanlinker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 简历仓库控制器
 */
@Controller
@RequestMapping("/background")
public class ListController {
    /**
     * 访问简历仓库页面
     * @return list.html
     */
    @RequestMapping("/list")
    public String list(){
        return "function/list";
    }
}
