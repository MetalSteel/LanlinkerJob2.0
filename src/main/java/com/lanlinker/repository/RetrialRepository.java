package com.lanlinker.repository;

import com.lanlinker.domain.Retrial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 复试对象持久层操作接口
 */
public interface RetrialRepository extends JpaRepository<Retrial,Integer>{

}
