package com.lanlinker.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 简历类
 */
@Entity
public class Resume {
    //主键编号
    @Id
    @GeneratedValue
    private Integer id;
    //应聘职位
    @NotNull(message = "应聘职位不能为空")
    @Column(nullable = false)
    private Integer job;
    //姓名
    @NotEmpty(message = "姓名不能为空")
    @Length(min = 2,max = 4,message = "姓名长度不合法")
    @Column(nullable = false)
    private String name;
    //身份证
    @Pattern(regexp = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)",message = "身份证格式不正确")
    @Column(nullable = false,unique = true)
    private String card;
    //户籍
    @NotEmpty(message = "户籍不能为空")
    @Length(min = 1,max = 32,message = "户籍长度不合法")
    @Column(nullable = false)
    private String register;
    //现居住地
    @NotEmpty(message = "现居住地不能为空")
    @Length(min = 1,max = 32,message = "现居住地长度不合法")
    @Column(nullable = false)
    private String address;
    //手机号
    @Pattern(regexp = "^1((((3[4-9])|(5[0-27-9])|(8[2-478])|(78)|(47))|((3[0-2])|([58][56])|(76)|(45))|(([35]3)|(8[019])|(77))|((170)))\\d{8})|(1349[0-9]{7})$",message = "手机号格式不正确")
    @Column(nullable = false)
    private String phone;
    //邮箱
    @Pattern(regexp = "^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$",message = "邮箱格式不正确")
    @Column(nullable = false)
    private String email;
    //QQ
    @NotEmpty(message = "QQ号不能为空")
    @Column(nullable = false)
    private String qq;
    //大学
    @NotEmpty(message = "大学不能为空")
    @Column(nullable = false)
    private String university;
    //学历
    @NotNull(message = "学历不能为空")
    @Column(nullable = false)
    private Integer education;
    //专业
    @NotEmpty(message = "专业不能为空")
    @Column(nullable = false)
    private String major;
    //工作年限
    @NotNull(message = "工作年限不能为空")
    @Column(nullable = false)
    private Integer workYear;
    //最低薪水
    @NotNull(message = "最低薪水不能为空")
    @Column(nullable = false)
    private Float workMinSalary;
    //最高薪水
    @NotNull(message = "最高薪水不能为空")
    @Column(nullable = false)
    private Float workMaxSalary;
    //入职时间
    @NotEmpty(message = "入职时间不能为空")
    @Column(nullable = false)
    private String workGoDate;
    //就职状态
    @NotNull(message = "就职状态不能为空")
    @Column(nullable = false)
    private boolean workStatus;
    //能否出差
    @NotNull(message = "出差状态不能为空")
    @Column(nullable = false)
    private boolean workTrip;
    //能否加班
    @NotNull(message = "加班状态不能为空")
    @Column(nullable = false)
    private boolean workOvertime;
    //个人备注
    @Column(nullable = false)
    private String remark;
    //简历状态 0初始 1初始通过 2初始未通过 3复试 4复试通过 5复试失败 6入职成功
    private Integer status;
    // 创建日期
    @Column(nullable = false)
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public Float getWorkMinSalary() {
        return workMinSalary;
    }

    public void setWorkMinSalary(Float workMinSalary) {
        this.workMinSalary = workMinSalary;
    }

    public Float getWorkMaxSalary() {
        return workMaxSalary;
    }

    public void setWorkMaxSalary(Float workMaxSalary) {
        this.workMaxSalary = workMaxSalary;
    }

    public String getWorkGoDate() {
        return workGoDate;
    }

    public void setWorkGoDate(String workGoDate) {
        this.workGoDate = workGoDate;
    }

    public boolean isWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(boolean workStatus) {
        this.workStatus = workStatus;
    }

    public boolean isWorkTrip() {
        return workTrip;
    }

    public void setWorkTrip(boolean workTrip) {
        this.workTrip = workTrip;
    }

    public boolean isWorkOvertime() {
        return workOvertime;
    }

    public void setWorkOvertime(boolean workOvertime) {
        this.workOvertime = workOvertime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
