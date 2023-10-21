package com.yh.service.impl;

import com.yh.mapper.PermissionMapper;
import com.yh.model.Permission;
import com.yh.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    /**
     * 添加权限
     *
     * @param permission
     */
    @Override
    public void save(Permission permission) {
        permissionMapper.save(permission);
    }

    /**
     * 根据id查找权限详情
     *
     * @param id
     * @return
     */
    @Override
    public Permission findById(Integer id) {
        return  permissionMapper.findById(id);
    }

    /**
     * 删除权限
     *
     * @param id
     */
    @Override
    public void deletePermission(Integer id) {
        permissionMapper.deletePermissionAndRole(id);
        permissionMapper.deletePermission(id);
    }
}
