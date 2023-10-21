package com.yh.mapper;

import com.yh.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionMapper {
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
     * 删除权限和角色关系表
     * @param id
     */
    void deletePermissionAndRole(Integer id);

    /**
     * 删除权限
     * @param id
     */
    void deletePermission(Integer id);
}
