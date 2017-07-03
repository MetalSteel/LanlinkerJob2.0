package com.lanlinker.rest;

import com.lanlinker.domain.Job;
import com.lanlinker.domain.JobVO;
import com.lanlinker.domain.Msg;
import com.lanlinker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Job对象Rest控制器
 */
@RestController
@RequestMapping("/rest")
public class JobRestController {
    /**
     * 注入Job对象业务层操作
     */
    @Autowired
    private JobService jobService;
    /**
     * 条件查询Job对象
     * @param pageIndex 当前记录索引
     * @param pageSize  每页记录数
     * @param sortName  排序名称
     * @param sortOrder 排序方式
     * @param job       查询条件
     * @return
     */
    @PostMapping("/findAllJobs")
    public JobVO findAllJobs(int pageIndex, int pageSize, String sortName, String sortOrder, Job job){
        return jobService.findAllJobs(pageIndex,pageSize,sortName,sortOrder,job);
    }
    /**
     * 查询Job对象
     * @return
     */
    @GetMapping("/findJobs")
    public List<Job> findJobs(){
        return jobService.findJobs();
    }
    /**
     * 根据Id删除Job对象
     * @param id
     * @return
     */
    @GetMapping("/deleteJobById")
    public Msg deleteJobById(@RequestParam("id") Integer id){
        jobService.deleteJobById(id);
        return new Msg("OK","职位删除成功");
    }

    /**
     * 添加职位
     * @param job
     * @param result
     * @return
     */
    @PostMapping("/addJob")
    public Msg addJob(@Valid Job job, BindingResult result){
        if(result.hasErrors()){
            return new Msg("ERROR",result.getFieldErrors().get(0).getDefaultMessage());
        }
        Job j = jobService.addJob(job);
        if(j!=null){
            return new Msg("OK","职位添加成功");
        }else {
            return new Msg("ERROR","职位添加失败");
        }
    }
    /**
     * 更新职位
     * @param job
     * @param result
     * @return
     */
    @PostMapping("/updateJob")
    public Msg updateJob(@Valid Job job, BindingResult result){
        if(result.hasErrors()){
            return new Msg("ERROR",result.getFieldErrors().get(0).getDefaultMessage());
        }
        Job j = jobService.addJob(job);
        if(j!=null){
            return new Msg("OK","职位修改成功");
        }else {
            return new Msg("ERROR","职位修改失败");
        }
    }
}
