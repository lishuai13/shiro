package com.lishuai.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lishuai.entity.User;
import com.lishuai.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义 Realm
 * @author lishuai
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取主凭证,也就是用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查找用户对象,包括List<Role>
        User user = userMapper.findRolesByUserName(username);

        //遍历权限，传入simpleAuthorizationInfo
        user.getRoles().forEach(role -> {
            simpleAuthorizationInfo.addRole(role.getName());
        });

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",principal);
        User user = userMapper.selectOne(queryWrapper);

        //如果数据库存在记录
        if(user!=null){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new MyByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
