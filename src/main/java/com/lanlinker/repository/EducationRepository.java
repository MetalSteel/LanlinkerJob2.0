package com.lanlinker.repository;

import com.lanlinker.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Education类持久层操作
 */
public interface EducationRepository extends JpaRepository<Education,Integer>,JpaSpecificationExecutor<Education>{

}
