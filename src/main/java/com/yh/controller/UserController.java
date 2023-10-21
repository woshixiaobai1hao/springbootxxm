package com.yh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.model.Role;
import com.yh.model.UserInfo;
import com.yh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 查询所有
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping ("findAll")
    public String findAll(Model model,
                          @RequestParam(value = "page",defaultValue = "1") Integer page ,
                          @RequestParam(value = "size",defaultValue = "5") Integer size){

        PageHelper.startPage(page,size);

        List<UserInfo> list =userService.findAll();

        PageInfo pageInfo = new PageInfo(list);

        model.addAttribute("pageInfo",pageInfo);

        return "user-list";
    }
    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @PostMapping("save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:/user/findAll";
    }
    /**
     * 根据用户id查找用户
     * @param id
     * @return
     */
    @GetMapping("findById")
    public String findById(@RequestParam("id") Integer id,Model model){
        UserInfo userInfo= userService.findById(id);
        model.addAttribute("user",userInfo);
        return "user-show";
    }
    /**
     * 根据用户id查找用户角色
     * @param id
     * @return
     */
    @GetMapping("findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(@RequestParam("id") Integer id,Model model){
           //得知道给谁进行添加角色
           UserInfo userInfo = userService.findById(id);

           model.addAttribute("user",userInfo);
           //根据用户id查询该用户哪些角色可以添加
           List<Role> roleList= userService.findOtherRole(id);

           model.addAttribute("roleList",roleList);

           return "user-role-add";
    }
    /**
     * 给用户添加角色
     * @param userId
     * @param ids
     * @return
     */
    @PostMapping("addRoleToUser")
    public String addRoleToUser(@RequestParam("userId") Integer userId,@RequestParam("ids") Integer[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:/user/findAll";
    }
    /**
     * 删除用户
     * @param id
     */
    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/user/findAll";
    }

    /**
     * 获取登录的用户名
     * @return
     */
    @RequestMapping("getUsername")
    @ResponseBody
    public String getUsername(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        return username;
    }
}
