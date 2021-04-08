package com.lishuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lishuai
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查出用户，包括用户角色信息
     * @param username
     * @return
     */
    User findRolesByUserName(String username);
}
