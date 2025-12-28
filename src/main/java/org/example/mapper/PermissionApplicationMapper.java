package org.example.mapper;

import org.example.entity.PermissionApplication;
import java.util.List;

/**
 * 权限申请Mapper接口
 */
public interface PermissionApplicationMapper {
    /**
     * 查询所有申请
     */
    List<PermissionApplication> findAll();

    /**
     * 根据ID查询申请
     */
    PermissionApplication findById(Integer id);

    /**
     * 根据用户ID查询申请
     */
    List<PermissionApplication> findByUserId(Integer userId);

    /**
     * 根据状态查询申请列表
     */
    List<PermissionApplication> findByStatus(String status);

    /**
     * 添加申请
     */
    int insert(PermissionApplication application);

    /**
     * 更新申请信息
     */
    int update(PermissionApplication application);

    /**
     * 删除申请
     */
    int deleteById(Integer id);
}

