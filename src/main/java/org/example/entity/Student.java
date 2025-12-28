package org.example.entity;

import java.util.Date;

/**
 * 学生实体类
 */
public class Student {
    private Integer id;          // 学生ID
    private Integer userId;      // 用户ID
    private String studentNo;    // 学号
    private Integer classId;     // 班级ID
    private Integer dormitoryId; // 宿舍ID
    private Date createTime;     // 创建时间
    private Date updateTime;     // 更新时间

    // 关联对象
    private User user;           // 用户信息
    private Class clazz;         // 班级信息
    private Dormitory dormitory; // 宿舍信息

    public Student() {
    }

    public Student(Integer id, Integer userId, String studentNo, Integer classId,
                   Integer dormitoryId, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.studentNo = studentNo;
        this.classId = classId;
        this.dormitoryId = dormitoryId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
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
        return "Student{" +
                "id=" + id +
                ", userId=" + userId +
                ", studentNo='" + studentNo + '\'' +
                ", classId=" + classId +
                ", dormitoryId=" + dormitoryId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

