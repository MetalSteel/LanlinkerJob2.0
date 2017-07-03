package com.lanlinker.repository;

import com.lanlinker.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 邮箱类持久层操作接口
 */
public interface EmailRepository extends JpaRepository<Email,Integer>{
}
