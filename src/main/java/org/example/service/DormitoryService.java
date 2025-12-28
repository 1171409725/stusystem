package org.example.service;

import org.example.entity.Dormitory;
import org.example.mapper.DormitoryMapper;
import org.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 宿舍Service
 */
public class DormitoryService {
    
    /**
     * 查询所有宿舍
     */
    public List<Dormitory> findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);
            return mapper.findAll();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据ID查询
     */
    public Dormitory findById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);
            return mapper.findById(id);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 添加宿舍
     */
    public boolean add(Dormitory dormitory) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);
            int result = mapper.insert(dormitory);
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
     * 更新宿舍
     */
    public boolean update(Dormitory dormitory) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);
            int result = mapper.update(dormitory);
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
     * 删除宿舍
     */
    public boolean delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);
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

    /**
     * 根据条件查询
     */
    public List<Dormitory> findByCondition(Dormitory dormitory) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            DormitoryMapper mapper = sqlSession.getMapper(DormitoryMapper.class);
            return mapper.findByCondition(dormitory);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}

