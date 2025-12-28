package org.example.service;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 用户Service
 */
public class UserService {

    /**
     * 查询所有用户
     */
    public List<User> findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findAll();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据ID查询
     */
    public User findById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findById(id);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据用户名查询
     */
    public User findByUsername(String username) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findByUsername(username);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 添加用户
     */
    public boolean add(User user) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.insert(user);
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
     * 更新用户
     */
    public boolean update(User user) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.update(user);
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
     * 更新密码
     */
    public boolean updatePassword(Integer id, String password) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.updatePassword(id, password);
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
     * 删除用户
     */
    public boolean delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
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

