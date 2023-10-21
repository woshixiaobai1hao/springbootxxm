package com.yh.service.impl;

import com.yh.mapper.UserMapper;
import com.yh.model.Role;
import com.yh.model.UserInfo;
import com.yh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录验证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=userMapper.loadUserByUsername(username);

        if(userInfo!=null){
          User user=  new User(userInfo.getUsername(),
                    userInfo.getPassword(),
                    userInfo.getStatus()==1? true:false,
                    true,
                    true,
                    true,
                    getAthorities(userInfo.getRoles()));
          return  user;
        }
        return null;
    }

    private Set<SimpleGrantedAuthority> getAthorities(List<Role> roles) {// 1 Admin User
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            System.out.println("角色："+role.getRoleName());
        }
        return authorities;
    }
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userMapper.findAll();
    }
    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @Override
    public void save(UserInfo userInfo) {
        //用户进行加密
        userInfo.setPassword( new BCryptPasswordEncoder().encode(userInfo.getPassword()) );

        userMapper.save(userInfo);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(int id) {
        return userMapper.findById(id);
    }

    /**
     * 添加角色前，查询当前用户
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo findUserByID(Integer id) {
        return userMapper. findUserByID(id);
    }

    /**
     * 添加角色前，查询当前用户可以添加的角色
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> findOtherRole(Integer id) {
        return userMapper.findOtherRole(id);
    }

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param ids
     * @return
     */
    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        for (Integer id : ids){
            userMapper.addRoleToUser(userId, id);
        }
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUserAndRole(id);
        userMapper.deleteUser(id);
    }
}
