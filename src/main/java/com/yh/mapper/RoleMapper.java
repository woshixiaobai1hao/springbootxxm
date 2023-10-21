package com.yh.mapper;

import com.yh.model.Permission;
import com.yh.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleMapper {
    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色
     *
     * @param role
     */
    void save(Role role);

    /**
     * 查询角色详情
     *
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 查询当前角色有哪些权限可以添加
     *
     * @param id
     * @return
     */
    List<Permission> findRoleByIdOtherPermission(Integer id);

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param id
     */
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 先删除角色关联权限表
     * @param id
     */
    void deleteRoleAndPermission(Integer id);

    /**
     * 删除角色表和用户表的关系
     * @param id
     */
    void deleteRoleAndUser(Integer id);
    /**
     * 在删除角色
     * @param id
     */
    void deleteRole(Integer id);


}

