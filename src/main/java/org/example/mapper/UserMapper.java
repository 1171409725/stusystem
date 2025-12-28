package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.User;
import java.util.List;

/**
 * 用户Mapper接口
 */
public interface UserMapper {
    /**
     * 查询所有用户
     */
    List<User> findAll();

    /**
     * 根据ID查询用户
     */
    User findById(Integer id);

    /**
     * 根据用户名查询用户
     */
    User findByUsername(String username);

    /**
     * 根据角色查询用户列表
     */
    List<User> findByRole(String role);

    /**
     * 添加用户
     */
    int insert(User user);

    /**
     * 更新用户信息
     */
    int update(User user);

    /**
     * 更新密码
     */
    int updatePassword(@Param("id") Integer id, @Param("password") String password);

    /**
     * 删除用户
     */
    int deleteById(Integer id);

    /**
     * 根据条件查询
     */
    List<User> findByCondition(User user);
}

