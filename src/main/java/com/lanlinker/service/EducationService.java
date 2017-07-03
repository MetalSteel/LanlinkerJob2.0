package com.lanlinker.service;

import com.lanlinker.domain.Education;
import com.lanlinker.domain.EducationVO;
import com.lanlinker.repository.EducationRepository;
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
 * Education对象业务层操作
 */
@Service
public class EducationService {
    /**
     * 注入Education持久层操作
     */
    @Autowired
    private EducationRepository EducationRepository;

    /**
     * 条件查询Education对象
     * @param pageIndex 当前记录
     * @param pageSize  每页记录数
     * @param education       查询条件
     * @return
     */
    public EducationVO findAllEducations(int pageIndex, int pageSize,String sortName,String sortOrder,final Education education){
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
        Specification<Education> specification = new Specification<Education>() {
            @Override
            public Predicate toPredicate(Root<Education> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> name = root.get("name");
                return criteriaBuilder.like(name.as(String.class),"%"+education.getName()+"%");
            }
        };

        // 查询分页
        Page<Education> page = EducationRepository.findAll(specification,pageAble);
        // 创建返回对象
        EducationVO EducationVO = new EducationVO();
        EducationVO.setTotal(page.getTotalElements());
        EducationVO.setRows(page.getContent());
        return EducationVO;
    }

    /**
     * 查找所有Education对象
     * @return
     */
    public List<Education> findEducations(){
        return EducationRepository.findAll();
    }
    /**
     * 根据Id删除Education对象
     * @param id ID
     */
    public void deleteEducationById(Integer id){
        EducationRepository.delete(id);
    }

    /**
     * 插入Education对象/更新Education对象
     * @param Education Education对象
     * @return    成功插入的Education对象
     */
    public Education addEducation(Education Education){
        return EducationRepository.save(Education);
    }
}
