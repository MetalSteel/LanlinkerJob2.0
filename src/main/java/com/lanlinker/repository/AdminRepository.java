package com.lanlinker.repository;

import com.lanlinker.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 管理员持久层操作接口
 */
public interface AdminRepository extends JpaRepository<Admin,Integer>{
    /**
     * 根据用户名和密码查询管理员对象
     * @param username 用户名
     * @param password 密码
     * @return 管理员对象
     */
    List<Admin> findAdminsByUsernameEqualsAndPasswordEquals(String username,String password);
}
