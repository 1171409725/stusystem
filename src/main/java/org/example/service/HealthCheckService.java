package org.example.service;

import org.example.entity.HealthCheck;
import org.example.mapper.HealthCheckMapper;
import org.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

/**
 * 卫生检查Service
 */
public class HealthCheckService {

    /**
     * 查询所有检查记录
     */
    public List<HealthCheck> findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            HealthCheckMapper mapper = sqlSession.getMapper(HealthCheckMapper.class);
            return mapper.findAll();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据ID查询
     */
    public HealthCheck findById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            HealthCheckMapper mapper = sqlSession.getMapper(HealthCheckMapper.class);
            return mapper.findById(id);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据宿舍ID查询
     */
    public List<HealthCheck> findByDormitoryId(Integer dormitoryId) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            HealthCheckMapper mapper = sqlSession.getMapper(HealthCheckMapper.class);
            return mapper.findByDormitoryId(dormitoryId);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 添加检查记录
     */
    public boolean add(HealthCheck healthCheck) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            // 根据评分确定等级
            if (healthCheck.getScore() != null) {
                BigDecimal score = healthCheck.getScore();
                if (score.compareTo(new BigDecimal("90")) >= 0) {
                    healthCheck.setGrade("优秀");
                } else if (score.compareTo(new BigDecimal("80")) >= 0) {
                    healthCheck.setGrade("良好");
                } else if (score.compareTo(new BigDecimal("60")) >= 0) {
                    healthCheck.setGrade("合格");
                } else {
                    healthCheck.setGrade("不合格");
                }
            }
            
            // 如果没有设置检查人员姓名，从用户表查询
            if (healthCheck.getCheckerId() != null && (healthCheck.getCheckerName() == null || healthCheck.getCheckerName().isEmpty())) {
                org.example.mapper.UserMapper userMapper = sqlSession.getMapper(org.example.mapper.UserMapper.class);
                org.example.entity.User checker = userMapper.findById(healthCheck.getCheckerId());
                if (checker != null) {
                    healthCheck.setCheckerName(checker.getName());
                }
            }
            
            HealthCheckMapper mapper = sqlSession.getMapper(HealthCheckMapper.class);
            int result = mapper.insert(healthCheck);
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
     * 更新检查记录
     */
    public boolean update(HealthCheck healthCheck) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            // 根据评分确定等级
            if (healthCheck.getScore() != null) {
                BigDecimal score = healthCheck.getScore();
                if (score.compareTo(new BigDecimal("90")) >= 0) {
                    healthCheck.setGrade("优秀");
                } else if (score.compareTo(new BigDecimal("80")) >= 0) {
                    healthCheck.setGrade("良好");
                } else if (score.compareTo(new BigDecimal("60")) >= 0) {
                    healthCheck.setGrade("合格");
                } else {
                    healthCheck.setGrade("不合格");
                }
            }
            
            // 如果没有设置检查人员姓名，从用户表查询
            if (healthCheck.getCheckerId() != null && (healthCheck.getCheckerName() == null || healthCheck.getCheckerName().isEmpty())) {
                org.example.mapper.UserMapper userMapper = sqlSession.getMapper(org.example.mapper.UserMapper.class);
                org.example.entity.User checker = userMapper.findById(healthCheck.getCheckerId());
                if (checker != null) {
                    healthCheck.setCheckerName(checker.getName());
                }
            }
            
            HealthCheckMapper mapper = sqlSession.getMapper(HealthCheckMapper.class);
            int result = mapper.update(healthCheck);
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
     * 删除检查记录
     */
    public boolean delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            HealthCheckMapper mapper = sqlSession.getMapper(HealthCheckMapper.class);
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

