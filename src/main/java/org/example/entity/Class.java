package org.example.entity;

import java.util.Date;

/**
 * 班级实体类
 */
public class Class {
    private Integer id;          // 班级ID
    private String classNo;      // 班级编号
    private String className;    // 班级名称
    private String major;        // 专业
    private String grade;        // 年级
    private String description;  // 班级描述
    private Date createTime;     // 创建时间
    private Date updateTime;     // 更新时间

    public Class() {
    }

    public Class(Integer id, String classNo, String className, String major, 
                 String grade, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.classNo = classNo;
        this.className = className;
        this.major = major;
        this.grade = grade;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

