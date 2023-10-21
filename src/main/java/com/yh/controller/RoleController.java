package com.yh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.model.Permission;
import com.yh.model.Role;
import com.yh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("findAll")
    public String findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "5") int rows,
                          Model  model) {
        PageHelper.startPage(page, rows);

        List<Role> roleList = roleService.findAll();

        PageInfo pageInfo = new PageInfo(roleList);

        model.addAttribute("pageInfo", pageInfo);

        return "role-list";
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    /**
     * 查询角色详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("findById")
    public String findById(@RequestParam("id") Integer id, Model model) {
       Role role=roleService.findById(id);
       model.addAttribute("role",role);
       return "role-show";
    }

    /**
     * 添加权限 先查找角色没有的权限，再添加到角色
     * @param id
     * @param model
     * @return
     */
    @GetMapping("findRoleByIdAndAllPermission")
    public String findRoleByIdAndAllPermission(@RequestParam("id") Integer id, Model model) {
//        先查询当前角色
        Role role=roleService.findById(id);
        model.addAttribute("role",role);
//        查询当前角色有哪些权限可以添加
        List<Permission> permissionList=roleService. findRoleByIdOtherPermission(id);
        model.addAttribute("permissionList", permissionList);

        return "role-permission-add";
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param ids
     * @return
     */
    @PostMapping("addPermissionToRole")
    public String addPermissionToRole(@RequestParam("roleId") Integer roleId ,
                                      @RequestParam("ids") Integer[] ids) {

        roleService.addPermissionToRole(roleId,ids);

        return "redirect:/role/findAll";
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @GetMapping("deleteRole")
    public String deleteRole(@RequestParam("id") Integer id) {
        roleService.deleteRole(id);
        return "redirect:/role/findAll";
    }
}
