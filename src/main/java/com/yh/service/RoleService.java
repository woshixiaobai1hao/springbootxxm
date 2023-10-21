package com.yh.service;

import com.yh.model.Permission;
import com.yh.model.Role;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    void save(Role role);

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 查询当前角色有哪些权限可以添加
     * @param id
     * @return
     */
    List<Permission> findRoleByIdOtherPermission(Integer id);

    /**
     * 给角色添加权限
     * @param roleId
     * @param ids
     */
    void addPermissionToRole(Integer roleId, Integer[] permissionIds);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(Integer id);
}
