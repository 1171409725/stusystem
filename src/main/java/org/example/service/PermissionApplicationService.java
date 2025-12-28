package org.example.service;

import org.example.entity.PermissionApplication;
import org.example.entity.User;
import org.example.mapper.PermissionApplicationMapper;
import org.example.mapper.UserMapper;
import org.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * 权限申请Service
 */
public class PermissionApplicationService {

    /**
     * 查询所有申请
     */
    public List<PermissionApplication> findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            PermissionApplicationMapper mapper = sqlSession.getMapper(PermissionApplicationMapper.class);
            return mapper.findAll();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据ID查询
     */
    public PermissionApplication findById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            PermissionApplicationMapper mapper = sqlSession.getMapper(PermissionApplicationMapper.class);
            return mapper.findById(id);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据用户ID查询
     */
    public List<PermissionApplication> findByUserId(Integer userId) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            PermissionApplicationMapper mapper = sqlSession.getMapper(PermissionApplicationMapper.class);
            return mapper.findByUserId(userId);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 根据状态查询
     */
    public List<PermissionApplication> findByStatus(String status) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            PermissionApplicationMapper mapper = sqlSession.getMapper(PermissionApplicationMapper.class);
            return mapper.findByStatus(status);
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 添加申请
     */
    public boolean add(PermissionApplication application) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            PermissionApplicationMapper mapper = sqlSession.getMapper(PermissionApplicationMapper.class);
            int result = mapper.insert(application);
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
     * 审核申请（通过或拒绝）
     */
    public boolean review(Integer applicationId, Integer reviewerId, String reviewerName, 
                         String status, String reviewComment) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            PermissionApplicationMapper mapper = sqlSession.getMapper(PermissionApplicationMapper.class);
            PermissionApplication application = mapper.findById(applicationId);
            if (application == null) {
                return false;
            }
            
            application.setReviewerId(reviewerId);
            application.setReviewerName(reviewerName);
            application.setStatus(status);
            application.setReviewComment(reviewComment);
            application.setReviewTime(new Date());
            
            int result = mapper.update(application);
            
            // 如果审核通过，更新用户角色
            if ("approved".equals(status) && "inspector".equals(application.getApplicationType())) {
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                User user = userMapper.findById(application.getUserId());
                if (user != null && "student".equals(user.getRole())) {
                    user.setRole("inspector");
                    userMapper.update(user);
                }
            }
            
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
     * 删除申请
     */
    public boolean delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            PermissionApplicationMapper mapper = sqlSession.getMapper(PermissionApplicationMapper.class);
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

