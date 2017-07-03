package com.lanlinker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 复试结果
 */
@Entity
public class Retrial {
    // 简历编号
    @Id
    private Integer id;
    // 是否通过复试
    private boolean pass;
    // 复试评语
    private String suggestion;
    // 预约邮件内容(通过=>有内容,未通过=>无内容)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
