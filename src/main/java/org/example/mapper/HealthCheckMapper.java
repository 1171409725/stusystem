package org.example.mapper;

import org.example.entity.HealthCheck;
import java.util.List;

/**
 * 卫生检查记录Mapper接口
 */
public interface HealthCheckMapper {
    /**
     * 查询所有检查记录
     */
    List<HealthCheck> findAll();

    /**
     * 根据ID查询检查记录
     */
    HealthCheck findById(Integer id);

    /**
     * 根据宿舍ID查询检查记录
     */
    List<HealthCheck> findByDormitoryId(Integer dormitoryId);

    /**
     * 根据检查日期查询
     */
    List<HealthCheck> findByCheckDate(String checkDate);

    /**
     * 添加检查记录
     */
    int insert(HealthCheck healthCheck);

    /**
     * 更新检查记录
     */
    int update(HealthCheck healthCheck);

    /**
     * 删除检查记录
     */
    int deleteById(Integer id);

    /**
     * 根据条件查询
     */
    List<HealthCheck> findByCondition(HealthCheck healthCheck);
}

