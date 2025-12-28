package org.example.mapper;

import org.example.entity.Class;
import java.util.List;

/**
 * 班级Mapper接口
 */
public interface ClassMapper {
    /**
     * 查询所有班级
     */
    List<Class> findAll();

    /**
     * 根据ID查询班级
     */
    Class findById(Integer id);

    /**
     * 根据班级编号查询
     */
    Class findByClassNo(String classNo);

    /**
     * 添加班级
     */
    int insert(Class clazz);

    /**
     * 更新班级信息
     */
    int update(Class clazz);

    /**
     * 删除班级
     */
    int deleteById(Integer id);

    /**
     * 根据条件查询
     */
    List<Class> findByCondition(Class clazz);
}

