package org.example.service;

import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 学生Service
 */
public class StudentService {

    /**
     * 查询所有学生
     */
    public List<Student> findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            return mapper.findAll();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据ID查询
     */
    public Student findById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            return mapper.findById(id);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据用户ID查询学生
     */
    public Student findByUserId(Integer userId) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            return mapper.findByUserId(userId);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据宿舍ID查询学生列表
     */
    public List<Student> findByDormitoryId(Integer dormitoryId) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            return mapper.findByDormitoryId(dormitoryId);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 添加学生
     */
    public boolean add(Student student) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            int result = mapper.insert(student);
            sqlSession.commit();
            return result > 0;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 更新学生
     */
    public boolean update(Student student) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            int result = mapper.update(student);
            sqlSession.commit();
            return result > 0;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 删除学生
     */
    public boolean delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            int result = mapper.deleteById(id);
            sqlSession.commit();
            return result > 0;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}

