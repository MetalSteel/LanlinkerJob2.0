package com.lanlinker.service;

import com.lanlinker.domain.Admin;
import com.lanlinker.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员业务层操作
 */
@Service
public class AdminService {

    //注入持久层操作
    @Autowired
    private AdminRepository adminRepository;

    /**
     * 登录
     * @param admin 管理员对象
     * @return 返回查询出来管理员对象
     */
    public Admin login(Admin admin){
        List<Admin> adminList = adminRepository.findAdminsByUsernameEqualsAndPasswordEquals(admin.getUsername(),admin.getPassword());
        if (adminList!=null&&adminList.size()>0){
            return adminList.get(0);
        }else {
            return null;
        }
    }
}
