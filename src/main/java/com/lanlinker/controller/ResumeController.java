package com.lanlinker.controller;

import com.lanlinker.domain.Msg;
import com.lanlinker.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 简历控制器
 */
@Controller
@RequestMapping("/background")
public class ResumeController {

    /**
     * 注入简历业务层操作对象
     */
    @Autowired
    private ResumeService resumeService;

    /**
     * 访问resume.html
     * @return
     */
    @GetMapping("/resume")
    public String resume(){
        return "function/resume";
    }

    /**
     * 上传笔试图片
     * @param imgs 图片文件
     * @param id  简历编号
     * @return
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public Msg uploadImg(MultipartFile[] imgs,Integer id){
        return resumeService.uploadImg(imgs,id);
    }

    /**
     * 上传附件简历
     * @param attachment 附件简历
     * @param id         简历编号
     * @return
     */
    @RequestMapping("/uploadAttachment")
    @ResponseBody
    public Msg uploadAttachment(MultipartFile attachment,Integer id){
        return resumeService.uploadAttachment(attachment,id);
    }

}
