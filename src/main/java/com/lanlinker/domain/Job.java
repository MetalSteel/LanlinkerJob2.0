package com.lanlinker.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 职位类
 */
@Entity
public class Job {
    //主键
    @Id
    @GeneratedValue
    private Integer id;
    //职位名称
    @NotEmpty(message = "职位不能为空")
    @Column(nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
