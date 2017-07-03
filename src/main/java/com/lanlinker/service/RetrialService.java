package com.lanlinker.service;

import com.lanlinker.domain.Msg;
import com.lanlinker.domain.Resume;
import com.lanlinker.domain.Retrial;
import com.lanlinker.repository.ResumeRepository;
import com.lanlinker.repository.RetrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 复试业务层操作接口
 */
@Service
public class RetrialService {
    /**
     * 注入复试对象持久层操作接口
     */
    @Autowired
    private RetrialRepository retrialRepository;
    /**
     * 注入简历对象持久层操作接口
     */
    @Autowired
    private ResumeRepository resumeRepository;
    /**
     * 注入邮件对象业务层操作接口
     */
    @Autowired
    private EmailService emailService;
    /**
     * 保存复试结果
     * @param retrial
     */
    @Transactional
    public void saveRetrial(Retrial retrial){
        // 查询简历对象
        Resume resume = resumeRepository.findOne(retrial.getId());
        // 判断是否通过复试
        if(!retrial.isPass()){
            // 未通过复试
            resume.setStatus(6);
        }else {
            // 通过复试
            resume.setStatus(7);
            // 发送邮件
            emailService.sendSimpleMailMessage(resume.getEmail(),resume.getName(),retrial.getEmail());
        }
        // 更新简历状态
        resumeRepository.save(resume);
        // 保存简历对象
        retrialRepository.save(retrial);
    }

    /**
     * 查看复试结果
     * @param id
     * @return
     */
    public Retrial findRetrialById(Integer id){
        return retrialRepository.findOne(id);
    }

}
