package com.yh.service;

import com.yh.model.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 查询所有
     * @return
     */
    List<Permission> findAll();

    /**
     * 添加权限
     * @param permission
     */
    void save(Permission permission);

    /**
     * 根据id查找权限详情
     * @param id
     * @return
     */
    Permission findById(Integer id);

    /**
     * 删除权限
     * @param id
     */
    void deletePermission(Integer id);
}
