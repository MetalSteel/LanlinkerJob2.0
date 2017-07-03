package com.lanlinker.controller;

import com.lanlinker.domain.Msg;
import com.lanlinker.domain.Preliminary;
import com.lanlinker.domain.Resume;
import com.lanlinker.domain.Retrial;
import com.lanlinker.service.PreliminaryService;
import com.lanlinker.service.ResumeService;
import com.lanlinker.service.RetrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 复试预约控制器
 */
@Controller
@RequestMapping("/background")
public class RetrialController {
    /**
     * 注入简历业务操作类
     */
    @Autowired
    private ResumeService resumeService;
    /**
     * 注入初试业务操作类
     */
    @Autowired
    private PreliminaryService preliminaryService;
    /**
     * 注入复试业务操作类
     */
    @Autowired
    private RetrialService retrialService;
    /**
     * 访问retrial.html
     * @return
     */
    @GetMapping("/retrial")
    public String retrial(){
        return "function/retrial";
    }
    /**
     * 根据状态查询简历列表
     * @param status
     * @return
     */
    @GetMapping("/findResumesByStatus")
    @ResponseBody
    public List<Resume> findResumesByStatus(Integer status){
        return resumeService.findResumesByStatus(status);
    }
    /**
     * 根据Id查找初试结果
     * @param id
     * @return
     */
    @GetMapping("/findPreliminaryById")
    @ResponseBody
    public Preliminary findPreliminaryById(Integer id){
        return preliminaryService.findPreliminaryById(id);
    }

    /**
     * 保存复试结果
     * @param retrial
     * @return
     */
    @RequestMapping("/saveRetrial")
    @ResponseBody
    public Msg saveRetrial(Retrial retrial){
        retrialService.saveRetrial(retrial);
        return new Msg("OK","复试结果已提交");
    }
    /**
     * 查看复试结果
     */
    @RequestMapping("/findRetrialById")
    @ResponseBody
    public Retrial findRetrialById(Integer id){
        return retrialService.findRetrialById(id);
    }
}
