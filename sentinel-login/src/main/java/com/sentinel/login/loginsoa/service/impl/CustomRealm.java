package com.sentinel.login.loginsoa.service.impl;


import com.sentinel.login.loginsoa.mapper.UserInfoMapper;
import com.sentinel.login.loginsoa.model.UserInfo;
import com.sentinel.login.loginsoa.model.UserInfoExample;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import utils.MD5Utils;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 授权管理
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

//		//从主体获取用户
//		String username=(String)principals.getPrimaryPrincipal();
//
//		//从数据库查询用户的角色
//		String rolse=userService.queryRolse(username);
//		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
//		simpleAuthorizationInfo.addRole(rolse);
//		return simpleAuthorizationInfo;
        return null;
    }


    /**
     * 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //从主体获取传过来的用户
        String username = (String) token.getPrincipal();

        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUnameEqualTo(username);


        //通过用户传过来从数据库进行密码
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);

        if (CollectionUtils.isEmpty(userInfos) || StringUtils.isEmpty(userInfos.get(0).getPassword())) {
            return null;
        }

        //加盐
        ByteSource salt = ByteSource.Util.bytes(username);
        String realmName = this.getName();//获取当前自定义的realm
        String passwordMD5 = new SimpleHash("MD5", "root", salt, 1024).toHex();

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, userInfos.get(0).getPassword(), salt, realmName);
        return simpleAuthenticationInfo;
    }

}