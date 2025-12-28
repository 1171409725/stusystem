package org.example.mapper;

import org.example.entity.Student;
import java.util.List;

/**
 * 学生Mapper接口
 */
public interface StudentMapper {
    /**
     * 查询所有学生
     */
    List<Student> findAll();

    /**
     * 根据ID查询学生
     */
    Student findById(Integer id);

    /**
     * 根据学号查询学生
     */
    Student findByStudentNo(String studentNo);

    /**
     * 根据用户ID查询学生
     */
    Student findByUserId(Integer userId);

    /**
     * 根据宿舍ID查询学生列表
     */
    List<Student> findByDormitoryId(Integer dormitoryId);

    /**
     * 添加学生
     */
    int insert(Student student);

    /**
     * 更新学生信息
     */
    int update(Student student);

    /**
     * 删除学生
     */
    int deleteById(Integer id);

    /**
     * 根据条件查询
     */
    List<Student> findByCondition(Student student);
}

