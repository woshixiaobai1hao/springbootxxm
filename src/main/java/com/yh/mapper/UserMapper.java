package com.yh.mapper;

import com.yh.model.Role;
import com.yh.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * 登录验证
     * @param username
     * @return
     */
    UserInfo loadUserByUsername(String username);

    /**
     * 查询所有
     * @return
     */
    List<UserInfo> findAll();
    /**
     * 新增用户
     * @param userInfo
     */
    void save(UserInfo userInfo);
    /**
     * 用户详情
     * @param id
     * @return
     */
    UserInfo findById(int id);

    /**
     * 添加角色前，查询当前用户
     * @param id
     * @return
     */
    UserInfo  findUserByID(Integer id);

    /**
     * 添加角色前，查询当前用户可以添加的角色
     * @param id
     * @return
     */
    List<Role> findOtherRole(Integer id);
    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     * @return
     */
    void addRoleToUser(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
    /**
     * 删除关联信息表
     * @param id
     */
    void deleteUserAndRole(@Param("id") Integer id);
    /**
     * 删除用户
     * @param id
     */
    void deleteUser(@Param("id") Integer id);
}
