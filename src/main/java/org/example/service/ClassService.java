package org.example.service;

import org.example.entity.Class;
import org.example.mapper.ClassMapper;
import org.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 班级Service
 */
public class ClassService {

    /**
     * 查询所有班级
     */
    public List<Class> findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
            return mapper.findAll();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据ID查询
     */
    public Class findById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
            return mapper.findById(id);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 添加班级
     */
    public boolean add(Class clazz) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
            int result = mapper.insert(clazz);
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
     * 更新班级
     */
    public boolean update(Class clazz) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
            int result = mapper.update(clazz);
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
     * 删除班级
     */
    public boolean delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);
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

