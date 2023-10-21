package com.yh.service;

import com.yh.model.Role;
import com.yh.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//继承 UserDetailsService 重写loadUserByUsername 完成认证
public interface UserService extends UserDetailsService {
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
    UserInfo findUserByID(Integer id);

    /**
     * 添加角色前，查询当前用户可以添加的角色
     * @param id
     * @return
     */
    List<Role> findOtherRole(Integer id);
    /**
     * 给用户添加角色
     * @param userId
     * @param ids
     * @return
     */
    void addRoleToUser(Integer userId, Integer[] ids);
    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);
}
