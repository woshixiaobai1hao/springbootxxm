package com.yh.service.impl;

import com.yh.mapper.RoleMapper;
import com.yh.model.Permission;
import com.yh.model.Role;
import com.yh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    /**
     * 添加角色
     *
     * @param role
     */
    @Override
    public void save(Role role) {
        roleMapper.save(role);
    }

    /**
     * 查询角色详情
     *
     * @param id
     * @return
     */
    @Override
    public Role findById(Integer id) {
        return  roleMapper.findById(id);
    }

    /**
     * 查询当前角色有哪些权限可以添加
     *
     * @param id
     * @return
     */
    @Override
    public List<Permission> findRoleByIdOtherPermission(Integer id) {
        return roleMapper.findRoleByIdOtherPermission(id);
    }

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for(Integer permissionId : permissionIds){
            roleMapper.addPermissionToRole(roleId, permissionId);
        }
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRoleAndPermission(id);
        roleMapper.deleteRoleAndUser(id);
        roleMapper.deleteRole(id);
    }
}
