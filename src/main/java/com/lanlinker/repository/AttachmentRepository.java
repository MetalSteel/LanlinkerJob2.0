package com.lanlinker.repository;

import com.lanlinker.domain.Attachment;
import com.lanlinker.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 附件类持久层操作接口
 */
public interface AttachmentRepository extends JpaRepository<Attachment,Integer>{
    Attachment findByUid(Integer uid);
}
