package com.lanlinker.repository;

import com.lanlinker.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

/**
 * 简历类持久层操作接口
 */
public interface ResumeRepository extends JpaRepository<Resume,Integer>,JpaSpecificationExecutor<Resume> {
    // 根据状态查询查询简历数量
    Integer countAllByStatus(Integer status);
    // 根据状态查询简历列表
    List<Resume> findAllByStatus(Integer status);
    // 根据职位和状态查询简历
    List<Resume> findAllByJobAndStatus(Integer job,Integer status);
    // 简历总数
    Integer countAllBy();
    // 今日新增
    Integer countAllByCreateDateIsGreaterThanEqualAndCreateDateLessThan(Date start,Date end);
}
