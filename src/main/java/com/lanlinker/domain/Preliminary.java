package com.lanlinker.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 初试记录
 */
@Entity
public class Preliminary {
    // 简历ID
    @Id
    private Integer id;
    // 是否通过初试
    @Column(nullable = false)
    private boolean pass;
    // 初试成绩
    @Column(nullable = false)
    private Float grade;
    // 初试评语
    private String suggestion;

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

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
