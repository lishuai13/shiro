package com.lishuai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.User;
import com.lishuai.mapper.UserMapper;
import com.lishuai.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service
 * @author lishuai
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User login(String username, String password){
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        queryWrapper.allEq(map);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    public User register(User user){
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userMapper.insert(user);
        return user;
    }

    /**
     * 根据用户名查找用户信息，包括用户角色
     * @param username
     * @return
     */
//    public List<User> findRolesByUserName(String username){
//        return userMapper.findRolesByUserName(username);
//    }
}

