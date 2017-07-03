package com.lanlinker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Email类
 */
@Entity
public class Email {
    // 主键
    @Id
    private Integer id;
    // 主机地址
    @Column(nullable = false)
    private String host;
    // 邮箱账号
    @Column(nullable = false)
    private String username;
    // 邮箱密码
    @Column(nullable = false)
    private String password;
    // 邮件主题设置
    private String subject;
    // 收件人称呼
    private String start;
    // 邮件发件人设置
    private String end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
