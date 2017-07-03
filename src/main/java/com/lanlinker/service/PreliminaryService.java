package com.lanlinker.service;

import com.lanlinker.domain.Attachment;
import com.lanlinker.domain.Picture;
import com.lanlinker.domain.Preliminary;
import com.lanlinker.domain.Resume;
import com.lanlinker.repository.AttachmentRepository;
import com.lanlinker.repository.PictureRepository;
import com.lanlinker.repository.PreliminaryRepository;
import com.lanlinker.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 初试业务层操作
 */
@Service
public class PreliminaryService {
    /**
     * 附件类持久层操作接口
     */
    @Autowired
    private AttachmentRepository attachmentRepository;
    /**
     * 图片类持久层操作接口
     */
    @Autowired
    private PictureRepository pictureRepository;
    /**
     * 简历类持久层操作接口
     */
    @Autowired
    private ResumeRepository resumeRepository;
    /**
     * 初试类持久层操作接口
     */
    @Autowired
    private PreliminaryRepository preliminaryRepository;
    /**
     * 根据ID查找附件
     */
    public Attachment findAttachmentByUid(Integer uid){
        return attachmentRepository.findByUid(uid);
    }
    /**
     * 根据ID查找图片
     */
    public List<Picture> findPictureByUid(Integer uid){
        return pictureRepository.findAllByUid(uid);
    }
    /**
     * 保存初试对象
     * @param preliminary
     */
    @Transactional
    public void savePreliminary(Preliminary preliminary){
        Resume resume = resumeRepository.findOne(preliminary.getId());
        if(!preliminary.isPass()){
            // 未通过初试,简历状态变为4
           resume.setStatus(4);
        }else{
            // 通过初试,简历状态变为5
            resume.setStatus(5);
        }
        // 更新简历状态
        resumeRepository.save(resume);
        // 保存初试结果
        preliminaryRepository.save(preliminary);
    }

    /**
     * 根据ID查找初试结果
     * @param id
     * @return
     */
    public Preliminary findPreliminaryById(Integer id){
         return preliminaryRepository.findOne(id);
    }
}
