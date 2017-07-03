package com.lanlinker.repository;

import com.lanlinker.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Job类持久层操作
 */
public interface JobRepository extends JpaRepository<Job,Integer>,JpaSpecificationExecutor<Job>{

}
