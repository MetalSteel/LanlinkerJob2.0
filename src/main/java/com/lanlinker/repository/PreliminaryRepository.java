package com.lanlinker.repository;

import com.lanlinker.domain.Preliminary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 初试对象持久层操作接口
 */
public interface PreliminaryRepository extends JpaRepository<Preliminary,Integer>{
}
