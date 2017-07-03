package com.lanlinker.rest;

import com.lanlinker.domain.*;
import com.lanlinker.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Resume对象Rest控制器
 */
@RestController
@RequestMapping("/rest")
public class ResumeRestController {
    /**
     * 注入Resume对象业务层操作
     */
    @Autowired
    private ResumeService resumeService;
    /**
     * 条件查询Education对象
     * @param pageIndex 当前记录索引
     * @param pageSize  每页记录数
     * @param sortName  排序名称
     * @param sortOrder 排序方式
     * @param resume    查询条件
     * @return
     */
    @PostMapping("/findAllResumes")
    public ResumeVO findAllResumes(int pageIndex, int pageSize, String sortName, String sortOrder,String filterDate, Resume resume) throws ParseException {
        if(filterDate!=null&&!filterDate.equals("NaN-NaN-NaN")){
            // 将日期转换
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(filterDate);
            // 设置
            resume.setCreateDate(date);
        }
        return resumeService.findAllResumes(pageIndex,pageSize,sortName,sortOrder,resume);
    }

    /**
     * 根据Id删除简历
     * @param id
     * @return
     */
    @RequestMapping("/deleteResumeById")
    public Msg deleteResumeById(Integer id){
        resumeService.deleteResumeById(id);
        return new Msg("OK","简历删除成功");
    }
    /**
     * 根据职位和状态查询简历列表
     * @param job    职位编号
     * @param status 状态编号
     * @return
     */
    @GetMapping("/findResumeByJobAndStatus")
    public List<Resume> findResumeByJobAndStatus(Integer job, Integer status){
        return resumeService.findResumeByJobAndStatus(job,status);
    }

    /**
     * 根据状态查询简历列表数量
     * @param status
     * @return
     */
    @GetMapping("/countResumesByStatus")
    public Msg countResumesByStatus(Integer status){
        Msg msg = new Msg();
        Integer count = resumeService.countResumesByStatus(status);
        msg.setMsg(count+"");
        return msg;
    }
}
