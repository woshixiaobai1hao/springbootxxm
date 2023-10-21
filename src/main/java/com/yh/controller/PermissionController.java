package com.yh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.model.Permission;
import com.yh.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有分页展示
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("findAll")
    public String findAll(Model  model,
                          @RequestParam (name="page",defaultValue="1") Integer page,
                          @RequestParam (name="size",defaultValue="5") Integer size){

        PageHelper.startPage(page,size);

        List<Permission> permissionList=permissionService.findAll();

        PageInfo pageInfo = new PageInfo(permissionList);

        model.addAttribute("pageInfo",pageInfo);

        return "permission-list";
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @PostMapping("save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }

    /**
     * 根据id查找详情
     * @param model
     * @param id
     * @return
     */
    @GetMapping("findById")
    public String findById(Model model,@RequestParam("id") Integer id){
        Permission permission=permissionService.findById(id);
        model.addAttribute("permission",permission);
        return "permission-show";
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @GetMapping("deletePermission")
    public String deletePermission(@RequestParam("id") Integer id){
        permissionService.deletePermission(id);
        return "redirect:/permission/findAll";
    }
}
