package com.shadow.realm;


import com.shadow.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


/**
 * 登录
 *
 * @author yy
 * @version 2016/12/20 13:34
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // String name = (String) token.getPrincipal();
        // User user = new User();
        // user.setName(name);
        // // 调用userService查询是否有此用户
        // user = userService.queryUser(user);
        // if (user == null) {
        //     // 抛出 帐号找不到异常
        //     throw new UnknownAccountException();
        // }
        // // 判断帐号是否锁定
        // if (Boolean.TRUE.equals(user.getLocked())) {
        //     // 抛出 帐号锁定异常
        //     throw new LockedAccountException();
        // }
        //
        // // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        // SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        //         user.getId(), // 用户名
        //         user.getPwd(), // 密码
        //         ByteSource.Util.bytes(user.getCredentialsSalt()),// salt=username+salt
        //         getName() // realm name
        // );
        return null;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}

