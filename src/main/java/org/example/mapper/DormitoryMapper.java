package org.example.mapper;

import org.example.entity.Dormitory;
import java.util.List;

/**
 * 宿舍Mapper接口
 */
public interface DormitoryMapper {
    /**
     * 查询所有宿舍
     */
    List<Dormitory> findAll();

    /**
     * 根据ID查询宿舍
     */
    Dormitory findById(Integer id);

    /**
     * 根据楼栋和房间号查询
     */
    Dormitory findByBuildingAndRoom(String building, String roomNumber);

    /**
     * 添加宿舍
     */
    int insert(Dormitory dormitory);

    /**
     * 更新宿舍信息
     */
    int update(Dormitory dormitory);

    /**
     * 删除宿舍
     */
    int deleteById(Integer id);

    /**
     * 根据条件查询
     */
    List<Dormitory> findByCondition(Dormitory dormitory);
}

