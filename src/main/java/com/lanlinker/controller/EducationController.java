package com.lanlinker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学历控制器
 */
@Controller
@RequestMapping("/background")
public class EducationController {

    /**
     * 访问education.html
     * @return
     */
    @GetMapping("/education")
    public String job(){
        return "function/education";
    }

}
