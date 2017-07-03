package com.lanlinker.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 系统管理员
 */
@Entity
public class Admin {
    //主键
    @Id
    @GeneratedValue
    private Integer id;
    //管理员名称
    @NotEmpty(message = "管理员名称不能为空")
    @Column(nullable = false,length = 32)
    private String username;
    //管理员密码
    @NotEmpty(message = "管理员密码不能为空")
    @Column(nullable = false,length = 32)
    private String password;
    //管理员头像
    @Column(nullable = false,length = 128)
    private String icon;

    public Admin() {
    }

    public Admin(String username, String password, String icon) {
        this.username = username;
        this.password = password;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
