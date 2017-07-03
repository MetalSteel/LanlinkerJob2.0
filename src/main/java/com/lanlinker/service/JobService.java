package com.lanlinker.service;

import com.lanlinker.domain.Job;
import com.lanlinker.domain.JobVO;
import com.lanlinker.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Job对象业务层操作
 */
@Service
public class JobService {
    /**
     * 注入Job持久层操作
     */
    @Autowired
    private JobRepository jobRepository;

    /**
     * 条件查询Job对象
     * @param pageIndex 当前记录
     * @param pageSize  每页记录数
     * @param job       查询条件
     * @return
     */
    public JobVO findAllJobs(int pageIndex, int pageSize,String sortName,String sortOrder,final Job job){
        // 计算当前页
        int pageNumber = pageIndex/pageSize;
        // 创建分页和添加排序条件
        Pageable pageAble = null;
        // 创建排序
        if(sortName!=null&&sortOrder!=null){
            Sort.Order order = new Sort.Order(sortOrder.equals("desc")?Sort.Direction.DESC: Sort.Direction.ASC,sortName);
            Sort sort = new Sort(order);
            pageAble = new PageRequest(pageNumber,pageSize,sort);
        }else{
            pageAble = new PageRequest(pageNumber,pageSize);
        }
        // 创建查询条件
        Specification<Job> specification = new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> name = root.get("name");
                return criteriaBuilder.like(name.as(String.class),"%"+job.getName()+"%");
            }
        };

        // 查询分页
        Page<Job> page = jobRepository.findAll(specification,pageAble);
        // 创建返回对象
        JobVO jobVO = new JobVO();
        jobVO.setTotal(page.getTotalElements());
        jobVO.setRows(page.getContent());
        return jobVO;
    }

    /**
     * 查找所有Job对象
     * @return
     */
    public List<Job> findJobs(){
        return jobRepository.findAll();
    }
    /**
     * 根据Id删除Job对象
     * @param id ID
     */
    public void deleteJobById(Integer id){
        jobRepository.delete(id);
    }

    /**
     * 插入Job对象/更新Job对象
     * @param job Job对象
     * @return    成功插入的Job对象
     */
    public Job addJob(Job job){
        return jobRepository.save(job);
    }
}
