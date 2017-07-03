package com.lanlinker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 职位控制器
 */
@Controller
@RequestMapping("/background")
public class JobController {

    /**
     * 访问job.html
     * @return
     */
    @GetMapping("/job")
    public String job(){
        return "function/job";
    }

}
