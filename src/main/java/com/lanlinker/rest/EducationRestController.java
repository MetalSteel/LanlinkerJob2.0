package com.lanlinker.rest;

import com.lanlinker.domain.Education;
import com.lanlinker.domain.EducationVO;
import com.lanlinker.domain.Msg;
import com.lanlinker.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Education对象Rest控制器
 */
@RestController
@RequestMapping("/rest")
public class EducationRestController {
    /**
     * 注入Education对象业务层操作
     */
    @Autowired
    private EducationService educationService;
    /**
     * 条件查询Education对象
     * @param pageIndex 当前记录索引
     * @param pageSize  每页记录数
     * @param sortName  排序名称
     * @param sortOrder 排序方式
     * @param education       查询条件
     * @return
     */
    @PostMapping("/findAllEducations")
    public EducationVO findAllEducations(int pageIndex, int pageSize, String sortName, String sortOrder, Education education){
        return educationService.findAllEducations(pageIndex,pageSize,sortName,sortOrder,education);
    }
    /**
     * 查询Education对象
     * @return
     */
    @GetMapping("/findEducations")
    public List<Education> findEducations(){
        return educationService.findEducations();
    }
    /**
     * 根据Id删除Education对象
     * @param id
     * @return
     */
    @GetMapping("/deleteEducationById")
    public Msg deleteEducationById(@RequestParam("id") Integer id){
        educationService.deleteEducationById(id);
        return new Msg("OK","学历删除成功");
    }

    /**
     * 添加学历
     * @param Education
     * @param result
     * @return
     */
    @PostMapping("/addEducation")
    public Msg addEducation(@Valid Education Education, BindingResult result){
        if(result.hasErrors()){
            return new Msg("ERROR",result.getFieldErrors().get(0).getDefaultMessage());
        }
        Education j = educationService.addEducation(Education);
        if(j!=null){
            return new Msg("OK","学历添加成功");
        }else {
            return new Msg("ERROR","学历添加失败");
        }
    }
    /**
     * 更新学历
     * @param education
     * @param result
     * @return
     */
    @PostMapping("/updateEducation")
    public Msg updateEducation(@Valid Education education, BindingResult result){
        if(result.hasErrors()){
            return new Msg("ERROR",result.getFieldErrors().get(0).getDefaultMessage());
        }
        Education j = educationService.addEducation(education);
        if(j!=null){
            return new Msg("OK","学历修改成功");
        }else {
            return new Msg("ERROR","学历修改失败");
        }
    }
}
